package com.vip.tycloud.dto.message;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 通知与互动 功能模块 - 消息任务 - 更新请求 DTO。
 */
@Data
public class TyMsgTaskUpdateReqDTO {

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
}


