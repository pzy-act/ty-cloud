package com.vip.tycloud.dto.artwork;

import java.time.LocalDate;
import lombok.Data;

/**
 * 陶艺作品管理 功能模块 - 上釉记录 - 新增请求 DTO。
 */
@Data
public class TyArtGlazeRecordCreateReqDTO {

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
}


