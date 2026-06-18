package com.vip.tycloud.entity.ai;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * 智能问答 功能模块 - 调用日志 - 实体类。
 */
@Data
public class TyAiChatRequestLog {

    private Long id;

    private String requestId;

    private Long userId;

    private Long sessionId;

    private Long messageId;

    private String provider;

    private String model;

    private String requestStatus;

    private Integer promptTokens;

    private Integer completionTokens;

    private Integer totalTokens;

    private Integer latencyMs;

    private String errorCode;

    private String errorMessage;

    private LocalDateTime createdTime;
}
