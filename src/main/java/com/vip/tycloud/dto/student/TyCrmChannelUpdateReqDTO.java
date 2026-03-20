package com.vip.tycloud.dto.student;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 学员管理 功能模块 - 渠道管理 - 更新请求 DTO。
 */
@Data
public class TyCrmChannelUpdateReqDTO {

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


