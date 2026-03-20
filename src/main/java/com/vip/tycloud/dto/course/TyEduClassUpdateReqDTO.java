package com.vip.tycloud.dto.course;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Data;

/**
 * 课程与班级 功能模块 - 班级管理 - 更新请求 DTO。
 */
@Data
public class TyEduClassUpdateReqDTO {

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
     * 班级编码
     */
    private String classCode;

    /**
     * 班级名称
     */
    private String className;

    /**
     * 课程ID
     */
    private Long courseId;

    /**
     * 老师ID
     */
    private Long teacherId;

    /**
     * 教室ID
     */
    private Long classroomId;

    /**
     * 开始日期
     */
    private LocalDate startDate;

    /**
     * 结束日期
     */
    private LocalDate endDate;

    /**
     * 最大学员数
     */
    private Integer maxStudents;

    /**
     * 班级类型
     */
    private String classType;

    /**
     * 状态
     */
    private Integer status;
}


