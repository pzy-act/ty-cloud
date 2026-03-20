package com.vip.tycloud.dto.teacher;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 教师与绩效 功能模块 - 提成规则 - 响应 DTO。
 */
@Data
public class TyHrCommissionRuleRespDTO {

    /**
     * 主键ID
     */
    private Long id;

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

    /**
     * 删除标记
     */
    private Integer isDeleted;

    /**
     * 创建人ID
     */
    private Long createdBy;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 更新人ID
     */
    private Long updatedBy;

    /**
     * 更新时间
     */
    private LocalDateTime updatedTime;
}


