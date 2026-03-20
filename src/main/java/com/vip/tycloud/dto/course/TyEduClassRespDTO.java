package com.vip.tycloud.dto.course;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 课程与班级 功能模块 - 班级管理 - 响应 DTO。
 */
@Data
public class TyEduClassRespDTO {

    /**
     * 主键ID
     */
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


