package com.vip.tycloud.service.ai.provider;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * AI 对话消息载体。
 */
@Data
@AllArgsConstructor
public class AiMessage {

    private String role;

    private String content;
}
