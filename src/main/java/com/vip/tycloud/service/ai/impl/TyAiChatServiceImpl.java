package com.vip.tycloud.service.ai.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.config.OpenAiProperties;
import com.vip.tycloud.dto.ai.TyAiChatMessageReqDTO;
import com.vip.tycloud.dto.ai.TyAiChatMessageRespDTO;
import com.vip.tycloud.dto.ai.TyAiModelRespDTO;
import com.vip.tycloud.dto.ai.TyAiChatSessionCreateReqDTO;
import com.vip.tycloud.dto.ai.TyAiChatSessionRespDTO;
import com.vip.tycloud.dto.ai.TyAiSendMessageRespDTO;
import com.vip.tycloud.entity.ai.TyAiChatMessage;
import com.vip.tycloud.entity.ai.TyAiChatRequestLog;
import com.vip.tycloud.entity.ai.TyAiChatSession;
import com.vip.tycloud.repository.ai.TyAiChatMessageRepository;
import com.vip.tycloud.repository.ai.TyAiChatRequestLogRepository;
import com.vip.tycloud.repository.ai.TyAiChatSessionRepository;
import com.vip.tycloud.security.LoginUser;
import com.vip.tycloud.service.ai.TyAiChatService;
import com.vip.tycloud.service.ai.TyAiQuotaService;
import com.vip.tycloud.service.ai.provider.AiChatRequest;
import com.vip.tycloud.service.ai.provider.AiChatResponse;
import com.vip.tycloud.service.ai.provider.AiMessage;
import com.vip.tycloud.service.ai.provider.AiProvider;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * 智能问答 功能模块 - 聊天服务实现类。
 */
@Service
@RequiredArgsConstructor
public class TyAiChatServiceImpl implements TyAiChatService {

    private final TyAiChatSessionRepository tyAiChatSessionRepository;

    private final TyAiChatMessageRepository tyAiChatMessageRepository;

    private final TyAiChatRequestLogRepository tyAiChatRequestLogRepository;

    private final TyAiQuotaService tyAiQuotaService;

    private final AiProvider aiProvider;

    private final OpenAiProperties openAiProperties;

    @Override
    public TyAiChatSessionRespDTO createSession(TyAiChatSessionCreateReqDTO req, Long operatorId, Long campusId) {
        if (Objects.isNull(req) || !StringUtils.hasText(req.getTitle())) {
            return null;
        }
        TyAiChatSession entity = new TyAiChatSession();
        entity.setUserId(operatorId);
        entity.setCampusId(campusId);
        entity.setTitle(req.getTitle().trim());
        entity.setChatType(StringUtils.hasText(req.getChatType()) ? req.getChatType().trim() : "general");
        entity.setModel(StringUtils.hasText(req.getModel()) ? req.getModel().trim() : defaultModel());
        entity.setSystemPrompt(req.getSystemPrompt());
        entity.setStatus(1);
        entity.setIsDeleted(0);
        entity.setCreatedBy(operatorId);
        entity.setUpdatedBy(operatorId);
        tyAiChatSessionRepository.save(entity);
        return toRespDTO(entity);
    }

    @Override
    public PageResultDTO<TyAiChatSessionRespDTO> pageSession(Long userId, Integer pageNumber, Integer pageSize) {
        long current = Objects.isNull(pageNumber) || pageNumber < 1 ? 1L : pageNumber;
        long size = Objects.isNull(pageSize) || pageSize < 1 ? 10L : pageSize;
        Page<TyAiChatSession> page = new Page<>(current, size);
        IPage<TyAiChatSession> pageResult = tyAiChatSessionRepository.page(page, Wrappers.<TyAiChatSession>lambdaQuery()
            .eq(TyAiChatSession::getUserId, userId)
            .eq(TyAiChatSession::getIsDeleted, 0)
            .orderByDesc(TyAiChatSession::getLastMessageTime, TyAiChatSession::getId));
        List<TyAiChatSessionRespDTO> records = pageResult.getRecords().stream()
            .map(this::toRespDTO)
            .collect(Collectors.toList());
        return PageResultDTO.of(pageResult.getTotal(), records);
    }

    @Override
    public List<TyAiChatMessageRespDTO> listMessages(Long userId, Long sessionId) {
        if (Objects.isNull(userId) || Objects.isNull(sessionId)) {
            return List.of();
        }
        TyAiChatSession session = tyAiChatSessionRepository.getById(sessionId);
        if (Objects.isNull(session) || !Objects.equals(session.getUserId(), userId) || Objects.equals(session.getIsDeleted(), 1)) {
            return List.of();
        }
        List<TyAiChatMessage> list = tyAiChatMessageRepository.list(Wrappers.<TyAiChatMessage>lambdaQuery()
            .eq(TyAiChatMessage::getSessionId, sessionId)
            .eq(TyAiChatMessage::getUserId, userId)
            .eq(TyAiChatMessage::getIsDeleted, 0)
            .orderByAsc(TyAiChatMessage::getId));
        return list.stream().map(this::toRespDTO).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public TyAiSendMessageRespDTO sendMessage(TyAiChatMessageReqDTO req, Long userId, Long campusId) {
        Long sessionId = Objects.nonNull(req) ? parseLongId(req.getSessionId()) : null;
        if (Objects.isNull(req) || Objects.isNull(userId) || Objects.isNull(sessionId) || !StringUtils.hasText(req.getContent())) {
            return null;
        }

        TyAiChatSession session = tyAiChatSessionRepository.getById(sessionId);
        if (Objects.isNull(session) || !Objects.equals(session.getUserId(), userId) || Objects.equals(session.getIsDeleted(), 1)) {
            return null;
        }

        tyAiQuotaService.ensureDefaultQuota(userId, userId);

        TyAiChatMessage userMessage = new TyAiChatMessage();
        userMessage.setSessionId(sessionId);
        userMessage.setUserId(userId);
        userMessage.setRole("user");
        userMessage.setContent(req.getContent().trim());
        userMessage.setContentType("markdown");
        userMessage.setStatus("success");
        userMessage.setPromptTokens(0);
        userMessage.setCompletionTokens(0);
        userMessage.setTotalTokens(0);
        userMessage.setIsDeleted(0);
        userMessage.setCreatedBy(userId);
        userMessage.setUpdatedBy(userId);
        tyAiChatMessageRepository.save(userMessage);

        List<AiMessage> messages = new ArrayList<>();
        if (StringUtils.hasText(session.getSystemPrompt())) {
            messages.add(new AiMessage("developer", session.getSystemPrompt().trim()));
        }
        messages.addAll(loadContextMessages(sessionId));

        int estimatedTokens = Math.max(1, req.getContent().length() / 2);
        if (!tyAiQuotaService.canConsume(userId, 1, estimatedTokens)) {
            throw new IllegalStateException("当前额度不足");
        }

        AiChatRequest aiChatRequest = new AiChatRequest();
        aiChatRequest.setRequestId(UUID.randomUUID().toString().replace("-", ""));
        aiChatRequest.setUserId(userId);
        aiChatRequest.setSessionId(sessionId);
        aiChatRequest.setModel(StringUtils.hasText(session.getModel()) ? session.getModel() : defaultModel());
        aiChatRequest.setMessages(messages);

        long start = System.currentTimeMillis();
        AiChatResponse aiChatResponse = aiProvider.chat(aiChatRequest);
        long latency = System.currentTimeMillis() - start;

        TyAiChatMessage assistantMessage = new TyAiChatMessage();
        assistantMessage.setSessionId(sessionId);
        assistantMessage.setUserId(userId);
        assistantMessage.setRole("assistant");
        assistantMessage.setContent(aiChatResponse.getContent());
        assistantMessage.setContentType("markdown");
        assistantMessage.setStatus("success");
        assistantMessage.setPromptTokens(aiChatResponse.getPromptTokens());
        assistantMessage.setCompletionTokens(aiChatResponse.getCompletionTokens());
        assistantMessage.setTotalTokens(aiChatResponse.getTotalTokens());
        assistantMessage.setFinishReason(aiChatResponse.getFinishReason());
        assistantMessage.setIsDeleted(0);
        assistantMessage.setCreatedBy(userId);
        assistantMessage.setUpdatedBy(userId);
        tyAiChatMessageRepository.save(assistantMessage);

        session.setLastMessageTime(LocalDateTime.now());
        session.setUpdatedBy(userId);
        session.setModel(aiChatRequest.getModel());
        tyAiChatSessionRepository.updateById(session);

        String requestId = aiChatRequest.getRequestId();
        TyAiChatRequestLog log = new TyAiChatRequestLog();
        log.setRequestId(requestId);
        log.setUserId(userId);
        log.setSessionId(sessionId);
        log.setMessageId(userMessage.getId());
        log.setProvider(Boolean.TRUE.equals(openAiProperties.getEnabled()) ? "openai" : "mock");
        log.setModel(aiChatRequest.getModel());
        log.setRequestStatus("success");
        log.setPromptTokens(aiChatResponse.getPromptTokens());
        log.setCompletionTokens(aiChatResponse.getCompletionTokens());
        log.setTotalTokens(aiChatResponse.getTotalTokens());
        log.setLatencyMs((int) latency);
        tyAiChatRequestLogRepository.save(log);

        boolean consumed = tyAiQuotaService.consume(userId, sessionId, assistantMessage.getId(), requestId,
            aiChatResponse.getPromptTokens(), aiChatResponse.getCompletionTokens(), userId);
        if (!consumed) {
            throw new IllegalStateException("当前额度不足");
        }

        TyAiSendMessageRespDTO respDTO = new TyAiSendMessageRespDTO();
        respDTO.setRequestId(requestId);
        respDTO.setSessionId(toIdString(sessionId));
        respDTO.setUserMessageId(toIdString(userMessage.getId()));
        respDTO.setAssistantMessageId(toIdString(assistantMessage.getId()));
        respDTO.setAnswer(aiChatResponse.getContent());
        respDTO.setPromptTokens(aiChatResponse.getPromptTokens());
        respDTO.setCompletionTokens(aiChatResponse.getCompletionTokens());
        respDTO.setTotalTokens(aiChatResponse.getTotalTokens());
        respDTO.setFinishReason(aiChatResponse.getFinishReason());
        return respDTO;
    }

    @Override
    public List<TyAiModelRespDTO> listModels() {
        List<OpenAiProperties.ModelOption> modelOptions = openAiProperties.getModels();
        if (CollectionUtils.isEmpty(modelOptions)) {
            TyAiModelRespDTO defaultOption = new TyAiModelRespDTO();
            defaultOption.setValue(defaultModel());
            defaultOption.setLabel(defaultModel());
            defaultOption.setDefaultModel(true);
            return List.of(defaultOption);
        }
        return modelOptions.stream()
            .filter(item -> Objects.nonNull(item) && StringUtils.hasText(item.getValue()))
            .map(item -> {
                TyAiModelRespDTO respDTO = new TyAiModelRespDTO();
                respDTO.setValue(item.getValue());
                respDTO.setLabel(StringUtils.hasText(item.getLabel()) ? item.getLabel() : item.getValue());
                respDTO.setDefaultModel(Boolean.TRUE.equals(item.getDefaultModel())
                    || item.getValue().equals(defaultModel()));
                return respDTO;
            })
            .collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteSession(Long userId, Long sessionId, Long operatorId) {
        if (Objects.isNull(userId) || Objects.isNull(sessionId)) {
            return false;
        }
        TyAiChatSession session = tyAiChatSessionRepository.getById(sessionId);
        if (Objects.isNull(session) || !Objects.equals(session.getUserId(), userId)) {
            return false;
        }
        boolean deleted = tyAiChatSessionRepository.logicDeleteById(sessionId, operatorId) > 0;
        if (deleted) {
            List<TyAiChatMessage> messages = tyAiChatMessageRepository.list(Wrappers.<TyAiChatMessage>lambdaQuery()
                .eq(TyAiChatMessage::getSessionId, sessionId)
                .eq(TyAiChatMessage::getIsDeleted, 0));
            for (TyAiChatMessage message : messages) {
                tyAiChatMessageRepository.logicDeleteById(message.getId(), operatorId);
            }
        }
        return deleted;
    }

    private List<AiMessage> loadContextMessages(Long sessionId) {
        List<TyAiChatMessage> messages = tyAiChatMessageRepository.list(Wrappers.<TyAiChatMessage>lambdaQuery()
            .eq(TyAiChatMessage::getSessionId, sessionId)
            .eq(TyAiChatMessage::getIsDeleted, 0)
            .orderByAsc(TyAiChatMessage::getId));
        if (CollectionUtils.isEmpty(messages)) {
            return new ArrayList<>();
        }
        int start = Math.max(0, messages.size() - 10);
        List<TyAiChatMessage> recent = messages.subList(start, messages.size());
        return recent.stream()
            .map(item -> new AiMessage(item.getRole(), item.getContent()))
            .collect(Collectors.toList());
    }

    private String defaultModel() {
        if (Boolean.TRUE.equals(openAiProperties.getEnabled()) && StringUtils.hasText(openAiProperties.getModel())) {
            return openAiProperties.getModel();
        }
        return "mock";
    }

    private TyAiChatSessionRespDTO toRespDTO(TyAiChatSession entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        TyAiChatSessionRespDTO respDTO = new TyAiChatSessionRespDTO();
        BeanUtils.copyProperties(entity, respDTO);
        respDTO.setId(toIdString(entity.getId()));
        respDTO.setUserId(toIdString(entity.getUserId()));
        respDTO.setCampusId(toIdString(entity.getCampusId()));
        return respDTO;
    }

    private TyAiChatMessageRespDTO toRespDTO(TyAiChatMessage entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        TyAiChatMessageRespDTO respDTO = new TyAiChatMessageRespDTO();
        BeanUtils.copyProperties(entity, respDTO);
        respDTO.setId(toIdString(entity.getId()));
        respDTO.setSessionId(toIdString(entity.getSessionId()));
        respDTO.setUserId(toIdString(entity.getUserId()));
        respDTO.setParentMessageId(toIdString(entity.getParentMessageId()));
        return respDTO;
    }

    private Long parseLongId(String id) {
        if (!StringUtils.hasText(id)) {
            return null;
        }
        try {
            return Long.valueOf(id.trim());
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    private String toIdString(Long id) {
        return Objects.isNull(id) ? null : String.valueOf(id);
    }
}
