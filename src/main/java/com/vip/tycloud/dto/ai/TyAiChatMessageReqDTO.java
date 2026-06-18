package com.vip.tycloud.dto.ai;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 智能问答 功能模块 - 发送消息 - 请求 DTO。
 */
@Data
public class TyAiChatMessageReqDTO {

    @NotBlank
    private String sessionId;

    @NotBlank
    private String content;
}
