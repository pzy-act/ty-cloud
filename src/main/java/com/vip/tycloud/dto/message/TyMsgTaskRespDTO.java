package com.vip.tycloud.dto.message;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * 通知与互动 功能模块 - 消息任务 - 响应 DTO。
 */
@Data
public class TyMsgTaskRespDTO {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 校区ID
     */
    private Long campusId;

    /**
     * 任务号
     */
    private String taskNo;

    /**
     * 模板ID
     */
    private Long templateId;

    /**
     * 发送类型
     */
    private String sendType;

    /**
     * 计划发送时间
     */
    private LocalDateTime scheduledTime;

    /**
     * 目标类型
     */
    private String targetType;

    /**
     * 内容快照
     */
    private String contentSnapshot;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 创建人ID
     */
    private Long creatorId;

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


