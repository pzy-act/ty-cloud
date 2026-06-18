package com.vip.tycloud.entity.ai;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * 智能问答 功能模块 - 消息 - 实体类。
 */
@Data
public class TyAiChatMessage {

    private Long id;

    private Long sessionId;

    private Long userId;

    private Long parentMessageId;

    private String role;

    private String content;

    private String contentType;

    private String status;

    private Integer promptTokens;

    private Integer completionTokens;

    private Integer totalTokens;

    private String finishReason;

    private String errorMessage;

    private Integer isDeleted;

    private Long createdBy;

    private LocalDateTime createdTime;

    private Long updatedBy;

    private LocalDateTime updatedTime;
}
