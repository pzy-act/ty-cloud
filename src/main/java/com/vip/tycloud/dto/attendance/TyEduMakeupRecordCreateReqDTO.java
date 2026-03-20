package com.vip.tycloud.dto.attendance;

import lombok.Data;

/**
 * 考勤与消课 功能模块 - 补课管理 - 新增请求 DTO。
 */
@Data
public class TyEduMakeupRecordCreateReqDTO {

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
}


