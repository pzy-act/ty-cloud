package com.vip.tycloud.dto.attendance;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 考勤与消课 功能模块 - 请假管理 - 更新请求 DTO。
 */
@Data
public class TyEduLeaveRequestUpdateReqDTO {

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
}


