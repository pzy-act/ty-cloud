package com.vip.tycloud.dto.schedule;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.Data;

/**
 * 排课与资源 功能模块 - 课表管理 - 响应 DTO。
 */
@Data
public class TyEduLessonRespDTO {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 校区ID
     */
    private Long campusId;

    /**
     * 班级ID
     */
    private Long classId;

    /**
     * 课时号
     */
    private Integer lessonNo;

    /**
     * 课时日期
     */
    private LocalDate lessonDate;

    /**
     * 开始时间
     */
    private LocalTime startTime;

    /**
     * 结束时间
     */
    private LocalTime endTime;

    /**
     * 老师ID
     */
    private Long teacherId;

    /**
     * 教室ID
     */
    private Long classroomId;

    /**
     * 课时主题
     */
    private String lessonTopic;

    /**
     * 课时状态
     */
    private Integer lessonStatus;

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


