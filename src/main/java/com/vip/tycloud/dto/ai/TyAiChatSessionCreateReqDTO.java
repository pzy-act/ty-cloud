package com.vip.tycloud.dto.ai;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 智能问答 功能模块 - 创建会话 - 请求 DTO。
 */
@Data
public class TyAiChatSessionCreateReqDTO {

    @NotBlank
    private String title;

    private String chatType;

    private String model;

    private String systemPrompt;
}
