package com.vip.tycloud.dto.teacher;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 教师与绩效 功能模块 - 教师档案 - 响应 DTO。
 */
@Data
public class TyHrTeacherProfileRespDTO {

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

    private String username;

    private String realName;

    private String mobile;

    private String jobNo;

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


