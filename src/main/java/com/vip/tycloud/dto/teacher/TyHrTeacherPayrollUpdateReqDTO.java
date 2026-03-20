package com.vip.tycloud.dto.teacher;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 教师与绩效 功能模块 - 工资核算 - 更新请求 DTO。
 */
@Data
public class TyHrTeacherPayrollUpdateReqDTO {

    /**
     * 主键ID
     */
    @NotNull(message = "主键ID不能为空")
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
}


