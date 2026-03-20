package com.vip.tycloud.dto.message;

import lombok.Data;

/**
 * 通知与互动 功能模块 - 消息模板 - 新增请求 DTO。
 */
@Data
public class TyMsgTemplateCreateReqDTO {

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


