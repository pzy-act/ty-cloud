package com.vip.tycloud.dto.message;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * 通知与互动 功能模块 - 家长反馈 - 响应 DTO。
 */
@Data
public class TyCrmFeedbackRespDTO {

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
     * 监护人ID
     */
    private Long guardianId;

    /**
     * 反馈类型
     */
    private String feedbackType;

    /**
     * 内容
     */
    private String content;

    /**
     * 评分
     */
    private Integer score;

    /**
     * 反馈时间
     */
    private LocalDateTime feedbackTime;

    /**
     * 处理人ID
     */
    private Long handlerId;

    /**
     * 处理状态
     */
    private Integer handleStatus;

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


