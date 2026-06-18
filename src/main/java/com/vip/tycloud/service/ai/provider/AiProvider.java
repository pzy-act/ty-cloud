package com.vip.tycloud.service.ai.provider;

/**
 * AI 模型 Provider 接口。
 */
public interface AiProvider {

    AiChatResponse chat(AiChatRequest request);
}
