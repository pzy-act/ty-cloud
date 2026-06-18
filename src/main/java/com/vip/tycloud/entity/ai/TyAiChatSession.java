package com.vip.tycloud.entity.ai;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * 智能问答 功能模块 - 会话 - 实体类。
 */
@Data
public class TyAiChatSession {

    private Long id;

    private Long userId;

    private Long campusId;

    private String title;

    private String chatType;

    private String model;

    private String systemPrompt;

    private String summary;

    private Integer status;

    private LocalDateTime lastMessageTime;

    private Integer isDeleted;

    private Long createdBy;

    private LocalDateTime createdTime;

    private Long updatedBy;

    private LocalDateTime updatedTime;
}
