package com.vip.tycloud.dto.message;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 通知与互动 功能模块 - 消息模板 - 更新请求 DTO。
 */
@Data
public class TyMsgTemplateUpdateReqDTO {

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
     * 模板编码
     */
    private String templateCode;

    /**
     * 模板名称
     */
    private String templateName;

    /**
     * 渠道类型
     */
    private String channelType;

    /**
     * 内容
     */
    private String content;

    /**
     * 状态
     */
    private Integer status;
}


