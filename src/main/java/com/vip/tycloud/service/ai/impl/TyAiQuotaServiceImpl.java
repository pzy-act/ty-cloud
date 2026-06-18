package com.vip.tycloud.service.ai.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.vip.tycloud.dto.ai.TyAiQuotaRespDTO;
import com.vip.tycloud.entity.ai.TyAiUserQuota;
import com.vip.tycloud.entity.ai.TyAiUsageRecord;
import com.vip.tycloud.repository.ai.TyAiUserQuotaRepository;
import com.vip.tycloud.repository.ai.TyAiUsageRecordRepository;
import com.vip.tycloud.service.ai.TyAiQuotaService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 智能问答 功能模块 - 额度管理 - 服务实现类。
 */
@Service
@RequiredArgsConstructor
public class TyAiQuotaServiceImpl implements TyAiQuotaService {

    private static final int DEFAULT_DAILY_QUESTION_LIMIT = 30;
    private static final int DEFAULT_DAILY_TOKEN_LIMIT = 100000;
    private static final int DEFAULT_MONTHLY_QUESTION_LIMIT = 500;
    private static final int DEFAULT_MONTHLY_TOKEN_LIMIT = 2000000;

    private final TyAiUserQuotaRepository tyAiUserQuotaRepository;

    private final TyAiUsageRecordRepository tyAiUsageRecordRepository;

    @Override
    public List<TyAiQuotaRespDTO> getMyQuota(Long userId) {
        if (Objects.isNull(userId)) {
            return List.of();
        }
        LocalDate today = LocalDate.now();
        List<TyAiQuotaRespDTO> result = new ArrayList<>();
        TyAiUserQuota daily = tyAiUserQuotaRepository.getByUserIdAndPeriod(userId, "daily", today, today);
        TyAiUserQuota monthly = tyAiUserQuotaRepository.getByUserIdAndPeriod(userId, "monthly", today.withDayOfMonth(1), YearMonth.from(today).atEndOfMonth());
        if (Objects.nonNull(daily)) {
            result.add(toRespDTO(daily));
        }
        if (Objects.nonNull(monthly)) {
            result.add(toRespDTO(monthly));
        }
        return result;
    }

    @Override
    public boolean ensureDefaultQuota(Long userId, Long operatorId) {
        if (Objects.isNull(userId)) {
            return false;
        }
        LocalDate today = LocalDate.now();
        ensureQuota(userId, "daily", today, today, DEFAULT_DAILY_QUESTION_LIMIT, DEFAULT_DAILY_TOKEN_LIMIT, operatorId);
        LocalDate monthStart = today.withDayOfMonth(1);
        LocalDate monthEnd = YearMonth.from(today).atEndOfMonth();
        ensureQuota(userId, "monthly", monthStart, monthEnd, DEFAULT_MONTHLY_QUESTION_LIMIT, DEFAULT_MONTHLY_TOKEN_LIMIT, operatorId);
        return true;
    }

    @Override
    public boolean canConsume(Long userId, Integer questionCount, Integer tokenCount) {
        if (Objects.isNull(userId)) {
            return false;
        }
        int actualQuestionCount = Math.max(1, Objects.isNull(questionCount) ? 1 : questionCount);
        int actualTokenCount = Math.max(0, Objects.isNull(tokenCount) ? 0 : tokenCount);
        for (TyAiUserQuota quota : listActiveQuota(userId)) {
            if (Objects.isNull(quota)) {
                continue;
            }
            int questionLimit = Objects.isNull(quota.getQuestionLimit()) ? 0 : quota.getQuestionLimit();
            int tokenLimit = Objects.isNull(quota.getTokenLimit()) ? 0 : quota.getTokenLimit();
            int usedQuestions = Objects.isNull(quota.getUsedQuestions()) ? 0 : quota.getUsedQuestions();
            int usedTokens = Objects.isNull(quota.getUsedTokens()) ? 0 : quota.getUsedTokens();
            if (questionLimit > 0 && usedQuestions + actualQuestionCount > questionLimit) {
                return false;
            }
            if (tokenLimit > 0 && usedTokens + actualTokenCount > tokenLimit) {
                return false;
            }
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean consume(Long userId, Long sessionId, Long messageId, String requestId, Integer promptTokens, Integer completionTokens, Long operatorId) {
        if (Objects.isNull(userId) || !org.springframework.util.StringUtils.hasText(requestId)) {
            return false;
        }
        int actualPromptTokens = Math.max(0, Objects.isNull(promptTokens) ? 0 : promptTokens);
        int actualCompletionTokens = Math.max(0, Objects.isNull(completionTokens) ? 0 : completionTokens);
        int totalTokens = actualPromptTokens + actualCompletionTokens;
        LocalDate today = LocalDate.now();
        boolean consumed = false;
        consumed = updateQuota(userId, "daily", today, today, 1, totalTokens, operatorId) || consumed;
        consumed = updateQuota(userId, "monthly", today.withDayOfMonth(1), YearMonth.from(today).atEndOfMonth(), 1, totalTokens, operatorId) || consumed;

        TyAiUsageRecord usageRecord = new TyAiUsageRecord();
        usageRecord.setUserId(userId);
        usageRecord.setSessionId(sessionId);
        usageRecord.setMessageId(messageId);
        usageRecord.setRequestId(requestId);
        usageRecord.setQuotaPeriod("daily_monthly");
        usageRecord.setQuestionCount(1);
        usageRecord.setPromptTokens(actualPromptTokens);
        usageRecord.setCompletionTokens(actualCompletionTokens);
        usageRecord.setTotalTokens(totalTokens);
        tyAiUsageRecordRepository.save(usageRecord);
        return consumed;
    }

    private List<TyAiUserQuota> listActiveQuota(Long userId) {
        LocalDate today = LocalDate.now();
        List<TyAiUserQuota> result = new ArrayList<>();
        TyAiUserQuota daily = tyAiUserQuotaRepository.getByUserIdAndPeriod(userId, "daily", today, today);
        TyAiUserQuota monthly = tyAiUserQuotaRepository.getByUserIdAndPeriod(userId, "monthly", today.withDayOfMonth(1), YearMonth.from(today).atEndOfMonth());
        if (Objects.nonNull(daily)) {
            result.add(daily);
        }
        if (Objects.nonNull(monthly)) {
            result.add(monthly);
        }
        return result;
    }

    private void ensureQuota(Long userId, String quotaPeriod, LocalDate periodStart, LocalDate periodEnd, int questionLimit, int tokenLimit, Long operatorId) {
        TyAiUserQuota quota = tyAiUserQuotaRepository.getByUserIdAndPeriod(userId, quotaPeriod, periodStart, periodEnd);
        if (Objects.nonNull(quota)) {
            return;
        }

        TyAiUserQuota newQuota = new TyAiUserQuota();
        newQuota.setUserId(userId);
        newQuota.setQuotaPeriod(quotaPeriod);
        newQuota.setQuestionLimit(questionLimit);
        newQuota.setTokenLimit(tokenLimit);
        newQuota.setUsedQuestions(0);
        newQuota.setUsedTokens(0);
        newQuota.setPeriodStart(periodStart);
        newQuota.setPeriodEnd(periodEnd);
        newQuota.setStatus(1);
        newQuota.setIsDeleted(0);
        newQuota.setCreatedBy(operatorId);
        newQuota.setUpdatedBy(operatorId);
        tyAiUserQuotaRepository.save(newQuota);
    }

    private boolean updateQuota(Long userId, String quotaPeriod, LocalDate periodStart, LocalDate periodEnd, int questionCount, int tokenCount, Long operatorId) {
        TyAiUserQuota quota = tyAiUserQuotaRepository.getByUserIdAndPeriod(userId, quotaPeriod, periodStart, periodEnd);
        if (Objects.isNull(quota)) {
            return false;
        }
        int actualQuestionLimit = Objects.isNull(quota.getQuestionLimit()) ? 0 : quota.getQuestionLimit();
        int actualTokenLimit = Objects.isNull(quota.getTokenLimit()) ? 0 : quota.getTokenLimit();
        int nextQuestions = (Objects.isNull(quota.getUsedQuestions()) ? 0 : quota.getUsedQuestions()) + questionCount;
        int nextTokens = (Objects.isNull(quota.getUsedTokens()) ? 0 : quota.getUsedTokens()) + tokenCount;
        if (actualQuestionLimit > 0 && nextQuestions > actualQuestionLimit) {
            return false;
        }
        if (actualTokenLimit > 0 && nextTokens > actualTokenLimit) {
            return false;
        }
        return tyAiUserQuotaRepository.increaseUsage(quota.getId(), questionCount, tokenCount, operatorId) > 0;
    }

    private TyAiQuotaRespDTO toRespDTO(TyAiUserQuota quota) {
        TyAiQuotaRespDTO respDTO = new TyAiQuotaRespDTO();
        respDTO.setUserId(quota.getUserId());
        respDTO.setQuotaPeriod(quota.getQuotaPeriod());
        respDTO.setQuestionLimit(quota.getQuestionLimit());
        respDTO.setTokenLimit(quota.getTokenLimit());
        respDTO.setUsedQuestions(quota.getUsedQuestions());
        respDTO.setUsedTokens(quota.getUsedTokens());
        respDTO.setPeriodStart(quota.getPeriodStart());
        respDTO.setPeriodEnd(quota.getPeriodEnd());
        return respDTO;
    }
}
