package com.vip.tycloud.entity.teacher;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.Data;

/**
 * 教师与绩效 功能模块 - 工资核算 - 实体类。
 */
@Data
public class TyHrTeacherPayroll {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 校区ID
     */
    private Long campusId;

    /**
     * 薪资月份
     */
    private String payrollMonth;

    /**
     * 老师ID
     */
    private Long teacherId;

    /**
     * 课时数量
     */
    private Integer lessonCount;

    /**
     * 出勤率
     */
    private BigDecimal attendanceRate;

    /**
     * 提成金额
     */
    private BigDecimal commissionAmount;

    /**
     * 扣减金额
     */
    private BigDecimal deductionAmount;

    /**
     * 应付金额
     */
    private BigDecimal payableAmount;

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


