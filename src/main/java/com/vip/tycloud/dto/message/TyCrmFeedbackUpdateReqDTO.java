package com.vip.tycloud.dto.message;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 通知与互动 功能模块 - 家长反馈 - 更新请求 DTO。
 */
@Data
public class TyCrmFeedbackUpdateReqDTO {

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
}


