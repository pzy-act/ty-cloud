package com.vip.tycloud.dto.ai;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * 智能问答 功能模块 - 会话 - 响应 DTO。
 */
@Data
public class TyAiChatSessionRespDTO {

    private String id;

    private String userId;

    private String campusId;

    private String title;

    private String chatType;

    private String model;

    private String systemPrompt;

    private String summary;

    private Integer status;

    private LocalDateTime lastMessageTime;
}
