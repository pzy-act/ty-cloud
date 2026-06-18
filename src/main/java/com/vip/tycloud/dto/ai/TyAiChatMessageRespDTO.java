package com.vip.tycloud.dto.ai;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * 智能问答 功能模块 - 消息 - 响应 DTO。
 */
@Data
public class TyAiChatMessageRespDTO {

    private String id;

    private String sessionId;

    private String userId;

    private String parentMessageId;

    private String role;

    private String content;

    private String contentType;

    private String status;

    private Integer promptTokens;

    private Integer completionTokens;

    private Integer totalTokens;

    private String finishReason;

    private String errorMessage;

    private LocalDateTime createdTime;
}
