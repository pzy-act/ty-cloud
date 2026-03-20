package com.vip.tycloud.dto.teacher;

import java.time.LocalDate;
import lombok.Data;

/**
 * 教师与绩效 功能模块 - 提成规则 - 新增请求 DTO。
 */
@Data
public class TyHrCommissionRuleCreateReqDTO {

    /**
     * 校区ID
     */
    private Long campusId;

    /**
     * 规则名称
     */
    private String ruleName;

    /**
     * 规则类型
     */
    private String ruleType;

    /**
     * 计算公式
     */
    private String calcFormula;

    /**
     * 生效开始日期
     */
    private LocalDate effectiveStart;

    /**
     * 生效结束日期
     */
    private LocalDate effectiveEnd;

    /**
     * 状态
     */
    private Integer status;
}


