package com.vip.tycloud.dto.attendance;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * 考勤与消课 功能模块 - 补课管理 - 响应 DTO。
 */
@Data
public class TyEduMakeupRecordRespDTO {

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
     * 原课时ID
     */
    private Long fromLessonId;

    /**
     * 目标课时ID
     */
    private Long toLessonId;

    /**
     * 原因
     */
    private String reason;

    /**
     * 申请人ID
     */
    private Long applyUserId;

    /**
     * 审批状态
     */
    private Integer approveStatus;

    /**
     * 审批人ID
     */
    private Long approveUserId;

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


