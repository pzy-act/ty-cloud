package com.vip.tycloud.dto.artwork;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 陶艺作品管理 功能模块 - 上釉记录 - 响应 DTO。
 */
@Data
public class TyArtGlazeRecordRespDTO {

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
     * 釉色编码
     */
    private String glazeColorCode;

    /**
     * 釉色名称
     */
    private String glazeColorName;

    /**
     * 上釉日期
     */
    private LocalDate glazeDate;

    /**
     * 操作人ID
     */
    private Long operatorId;

    /**
     * 备注
     */
    private String remark;

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


