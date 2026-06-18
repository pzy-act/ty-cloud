package com.vip.tycloud.dto.ai;

import java.time.LocalDate;
import lombok.Data;

/**
 * 智能问答 功能模块 - 用户额度 - 响应 DTO。
 */
@Data
public class TyAiQuotaRespDTO {

    private Long userId;

    private String quotaPeriod;

    private Integer questionLimit;

    private Integer tokenLimit;

    private Integer usedQuestions;

    private Integer usedTokens;

    private LocalDate periodStart;

    private LocalDate periodEnd;
}
