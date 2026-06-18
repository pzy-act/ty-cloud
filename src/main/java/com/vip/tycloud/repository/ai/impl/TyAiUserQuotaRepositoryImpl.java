package com.vip.tycloud.repository.ai.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.ai.TyAiUserQuota;
import com.vip.tycloud.mapper.ai.TyAiUserQuotaMapper;
import com.vip.tycloud.repository.ai.TyAiUserQuotaRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * 智能问答 功能模块 - 用户额度 - 仓储实现类。
 */
@Repository
public class TyAiUserQuotaRepositoryImpl extends BaseRepositoryImpl<TyAiUserQuotaMapper, TyAiUserQuota> implements TyAiUserQuotaRepository {

    public TyAiUserQuotaRepositoryImpl(TyAiUserQuotaMapper baseMapper) {
        super(baseMapper);
    }

    @Override
    public List<TyAiUserQuota> listCurrentByUserId(Long userId, LocalDate today) {
        LambdaQueryWrapper<TyAiUserQuota> queryWrapper = Wrappers.<TyAiUserQuota>lambdaQuery()
            .eq(TyAiUserQuota::getUserId, userId)
            .eq(TyAiUserQuota::getIsDeleted, 0)
            .eq(TyAiUserQuota::getStatus, 1)
            .eq(TyAiUserQuota::getPeriodStart, today)
            .eq(TyAiUserQuota::getPeriodEnd, today);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public TyAiUserQuota getByUserIdAndPeriod(Long userId, String quotaPeriod, LocalDate periodStart, LocalDate periodEnd) {
        return baseMapper.selectOne(Wrappers.<TyAiUserQuota>lambdaQuery()
            .eq(TyAiUserQuota::getUserId, userId)
            .eq(TyAiUserQuota::getQuotaPeriod, quotaPeriod)
            .eq(TyAiUserQuota::getPeriodStart, periodStart)
            .eq(TyAiUserQuota::getPeriodEnd, periodEnd)
            .eq(TyAiUserQuota::getIsDeleted, 0)
            .last("limit 1"));
    }

    @Override
    public int increaseUsage(Long id, Integer questionCount, Integer tokenCount, Long operatorId) {
        UpdateWrapper<TyAiUserQuota> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id)
            .eq("is_deleted", 0)
            .setSql("used_questions = used_questions + " + Math.max(0, questionCount == null ? 0 : questionCount))
            .setSql("used_tokens = used_tokens + " + Math.max(0, tokenCount == null ? 0 : tokenCount))
            .set("updated_by", operatorId)
            .set("updated_time", LocalDateTime.now());
        return baseMapper.update(null, updateWrapper);
    }
}
