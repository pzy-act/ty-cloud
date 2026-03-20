package com.vip.tycloud.dto.report;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 数据报表 功能模块 - 每日经营看板 - 响应 DTO。
 */
@Data
public class TyRptDailyKpiRespDTO {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 校区ID
     */
    private Long campusId;

    /**
     * 统计日期
     */
    private LocalDate statDate;

    /**
     * 新增线索数
     */
    private Integer newLeads;

    /**
     * 新增学员数
     */
    private Integer newStudents;

    /**
     * 转化率
     */
    private BigDecimal conversionRate;

    /**
     * 满班率
     */
    private BigDecimal classFillRate;

    /**
     * 出勤率
     */
    private BigDecimal attendanceRate;

    /**
     * 续费率
     */
    private BigDecimal renewalRate;

    /**
     * 营收金额
     */
    private BigDecimal revenueAmount;

    /**
     * 成本金额
     */
    private BigDecimal costAmount;

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


