package com.vip.tycloud.dto.attendance;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 考勤与消课 功能模块 - 补课管理 - 更新请求 DTO。
 */
@Data
public class TyEduMakeupRecordUpdateReqDTO {

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


