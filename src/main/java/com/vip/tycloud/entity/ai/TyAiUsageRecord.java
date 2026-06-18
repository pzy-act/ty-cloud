package com.vip.tycloud.entity.ai;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * 智能问答 功能模块 - 用量流水 - 实体类。
 */
@Data
public class TyAiUsageRecord {

    private Long id;

    private Long userId;

    private Long sessionId;

    private Long messageId;

    private String requestId;

    private String quotaPeriod;

    private Integer questionCount;

    private Integer promptTokens;

    private Integer completionTokens;

    private Integer totalTokens;

    private LocalDateTime createdTime;
}
