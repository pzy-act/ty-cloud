package com.vip.tycloud.service.ai.provider;

import lombok.Data;

/**
 * AI 对话响应。
 */
@Data
public class AiChatResponse {

    private String content;

    private Integer promptTokens;

    private Integer completionTokens;

    private Integer totalTokens;

    private String finishReason;
}
