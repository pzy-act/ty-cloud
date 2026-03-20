package com.vip.tycloud.entity.teacher;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.Data;

/**
 * 教师与绩效 功能模块 - 教师档案 - 实体类。
 */
@Data
public class TyHrTeacherProfile {

    /**
     * 主键ID
     */
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


