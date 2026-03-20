package com.vip.tycloud.dto.attendance;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * 考勤与消课 功能模块 - 签到考勤 - 响应 DTO。
 */
@Data
public class TyEduAttendanceRespDTO {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 校区ID
     */
    private Long campusId;

    /**
     * 课时ID
     */
    private Long lessonId;

    /**
     * 学员ID
     */
    private Long studentId;

    /**
     * 出勤状态
     */
    private Integer attendanceStatus;

    /**
     * 签到时间
     */
    private LocalDateTime checkinTime;

    /**
     * 签退时间
     */
    private LocalDateTime checkoutTime;

    /**
     * 操作人ID
     */
    private Long operatorId;

    /**
     * 备注
     */
    private String remark;

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


