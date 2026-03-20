package com.vip.tycloud.entity.student;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.Data;

/**
 * 学员管理 功能模块 - 线索跟进 - 实体类。
 */
@Data
public class TyCrmLeadFollow {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 校区ID
     */
    private Long campusId;

    /**
     * 线索ID
     */
    private Long leadId;

    /**
     * 跟进类型
     */
    private String followType;

    /**
     * 内容
     */
    private String content;

    /**
     * 跟进时间
     */
    private LocalDateTime followTime;

    /**
     * 下次跟进时间
     */
    private LocalDateTime nextFollowTime;

    /**
     * 跟进人ID
     */
    private Long followerId;

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


