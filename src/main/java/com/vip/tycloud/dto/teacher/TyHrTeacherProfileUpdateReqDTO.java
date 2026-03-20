package com.vip.tycloud.dto.teacher;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

/**
 * 教师与绩效 功能模块 - 教师档案 - 更新请求 DTO。
 */
@Data
public class TyHrTeacherProfileUpdateReqDTO {

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
     * 用户ID
     */
    private Long userId;

    /**
     * 老师等级
     */
    private String teacherLevel;

    /**
     * 入职日期
     */
    private LocalDate entryDate;

    /**
     * 基础薪资
     */
    private BigDecimal baseSalary;

    /**
     * 状态
     */
    private Integer status;
}


