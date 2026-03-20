package com.vip.tycloud.dto.artwork;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 陶艺作品管理 功能模块 - 取件记录 - 更新请求 DTO。
 */
@Data
public class TyArtPickupRecordUpdateReqDTO {

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
}


