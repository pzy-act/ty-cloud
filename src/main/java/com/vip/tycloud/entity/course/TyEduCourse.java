package com.vip.tycloud.entity.course;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.Data;

/**
 * 课程与班级 功能模块 - 课程管理 - 实体类。
 */
@Data
public class TyEduCourse {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 校区ID
     */
    private Long campusId;

    /**
     * 课程编码
     */
    private String courseCode;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 工艺类型
     */
    private String craftType;

    /**
     * 难度等级
     */
    private String difficultyLevel;

    /**
     * 课时时长（分钟）
     */
    private Integer lessonDurationMin;

    /**
     * 标准价格
     */
    private BigDecimal standardPrice;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 描述
     */
    private String description;

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


