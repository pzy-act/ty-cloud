package com.vip.tycloud.dto.artwork;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * 陶艺作品管理 功能模块 - 作品建档 - 响应 DTO。
 */
@Data
public class TyArtArtworkRespDTO {

    /**
     * 主键ID
     */
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


