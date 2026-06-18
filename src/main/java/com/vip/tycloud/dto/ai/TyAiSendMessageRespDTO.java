package com.vip.tycloud.dto.ai;

import lombok.Data;

/**
 * 智能问答 功能模块 - 发送消息 - 响应 DTO。
 */
@Data
public class TyAiSendMessageRespDTO {

    private String requestId;

    private String sessionId;

    private String userMessageId;

    private String assistantMessageId;

    private String answer;

    private Integer promptTokens;

    private Integer completionTokens;

    private Integer totalTokens;

    private String finishReason;
}
