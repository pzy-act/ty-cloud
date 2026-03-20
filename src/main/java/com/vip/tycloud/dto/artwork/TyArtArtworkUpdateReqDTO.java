package com.vip.tycloud.dto.artwork;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 陶艺作品管理 功能模块 - 作品建档 - 更新请求 DTO。
 */
@Data
public class TyArtArtworkUpdateReqDTO {

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
     * 作品编号
     */
    private String artworkNo;

    /**
     * 学员ID
     */
    private Long studentId;

    /**
     * 课时ID
     */
    private Long lessonId;

    /**
     * 作品名称
     */
    private String artworkName;

    /**
     * 泥料类型
     */
    private String clayType;

    /**
     * 尺寸规格
     */
    private String sizeSpec;

    /**
     * 当前状态
     */
    private Integer currentStatus;

    /**
     * 图片地址
     */
    private String photoUrl;
}


