package com.vip.tycloud.dto.schedule;

import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Data;

/**
 * 排课与资源 功能模块 - 课表管理 - 新增请求 DTO。
 */
@Data
public class TyEduLessonCreateReqDTO {

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
}


