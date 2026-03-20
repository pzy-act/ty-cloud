package com.vip.tycloud.dto.artwork;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Data;

/**
 * 陶艺作品管理 功能模块 - 上釉记录 - 更新请求 DTO。
 */
@Data
public class TyArtGlazeRecordUpdateReqDTO {

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


