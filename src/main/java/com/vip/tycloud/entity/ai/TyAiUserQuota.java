package com.vip.tycloud.entity.ai;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 智能问答 功能模块 - 用户额度 - 实体类。
 */
@Data
public class TyAiUserQuota {

    private Long id;

    private Long userId;

    private String quotaPeriod;

    private Integer questionLimit;

    private Integer tokenLimit;

    private Integer usedQuestions;

    private Integer usedTokens;

    private LocalDate periodStart;

    private LocalDate periodEnd;

    private Integer status;

    private Integer isDeleted;

    private Long createdBy;

    private LocalDateTime createdTime;

    private Long updatedBy;

    private LocalDateTime updatedTime;
}
