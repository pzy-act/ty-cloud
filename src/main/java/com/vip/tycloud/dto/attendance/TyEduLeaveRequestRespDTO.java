package com.vip.tycloud.dto.attendance;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * 考勤与消课 功能模块 - 请假管理 - 响应 DTO。
 */
@Data
public class TyEduLeaveRequestRespDTO {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 校区ID
     */
    private Long campusId;

    /**
     * 学员ID
     */
    private Long studentId;

    /**
     * 班级ID
     */
    private Long classId;

    /**
     * 课时ID
     */
    private Long lessonId;

    /**
     * 请假类型
     */
    private String leaveType;

    /**
     * 请假原因
     */
    private String leaveReason;

    /**
     * 申请时间
     */
    private LocalDateTime applyTime;

    /**
     * 审批状态
     */
    private Integer approveStatus;

    /**
     * 审批人ID
     */
    private Long approveUserId;

    /**
     * 审批时间
     */
    private LocalDateTime approveTime;

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


