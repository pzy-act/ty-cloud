package com.vip.tycloud.service.ai.provider;

import java.util.List;
import lombok.Data;

/**
 * AI 对话请求。
 */
@Data
public class AiChatRequest {

    private String requestId;

    private Long userId;

    private Long sessionId;

    private String model;

    private List<AiMessage> messages;
}
