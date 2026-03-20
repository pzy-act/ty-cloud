package com.vip.tycloud.dto.artwork;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 陶艺作品管理 功能模块 - 破损记录 - 更新请求 DTO。
 */
@Data
public class TyArtDamageRecordUpdateReqDTO {

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
     * 损坏时间
     */
    private LocalDateTime damageTime;

    /**
     * 损坏等级
     */
    private String damageLevel;

    /**
     * 损坏原因
     */
    private String damageReason;

    /**
     * 补偿方案
     */
    private String compensationPlan;

    /**
     * 处理人ID
     */
    private Long handledBy;

    /**
     * 处理时间
     */
    private LocalDateTime handledTime;
}


