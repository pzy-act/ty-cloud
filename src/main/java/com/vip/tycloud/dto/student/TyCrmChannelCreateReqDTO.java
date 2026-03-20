package com.vip.tycloud.dto.student;

import lombok.Data;

/**
 * 学员管理 功能模块 - 渠道管理 - 新增请求 DTO。
 */
@Data
public class TyCrmChannelCreateReqDTO {

    /**
     * 校区ID
     */
    private Long campusId;

    /**
     * 渠道编码
     */
    private String channelCode;

    /**
     * 渠道名称
     */
    private String channelName;

    /**
     * 渠道类型
     */
    private String channelType;

    /**
     * 负责人用户ID
     */
    private Long ownerUserId;

    /**
     * 状态
     */
    private Integer status;
}


