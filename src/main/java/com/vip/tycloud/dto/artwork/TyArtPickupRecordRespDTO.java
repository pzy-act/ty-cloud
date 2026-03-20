package com.vip.tycloud.dto.artwork;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * 陶艺作品管理 功能模块 - 取件记录 - 响应 DTO。
 */
@Data
public class TyArtPickupRecordRespDTO {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 校区ID
     */
    private Long campusId;

    /**
     * 作品ID
     */
    private Long artworkId;

    /**
     * 通知时间
     */
    private LocalDateTime notifyTime;

    /**
     * 取件时间
     */
    private LocalDateTime pickupTime;

    /**
     * 领取人姓名
     */
    private String receiverName;

    /**
     * 领取人手机号
     */
    private String receiverMobile;

    /**
     * 状态
     */
    private Integer status;

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


