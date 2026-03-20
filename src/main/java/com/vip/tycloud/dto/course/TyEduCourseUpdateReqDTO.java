package com.vip.tycloud.dto.course;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 课程与班级 功能模块 - 课程管理 - 更新请求 DTO。
 */
@Data
public class TyEduCourseUpdateReqDTO {

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
}


