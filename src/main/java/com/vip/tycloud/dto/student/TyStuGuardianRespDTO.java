package com.vip.tycloud.dto.student;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * 学员管理 功能模块 - 监护人管理 - 响应 DTO。
 */
@Data
public class TyStuGuardianRespDTO {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 校区ID
     */
    private Long campusId;

    /**
     * 监护人名称
     */
    private String guardianName;

    /**
     * 关系类型
     */
    private String relationType;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 微信
     */
    private String wechat;

    /**
     * 地址
     */
    private String address;

    /**
     * 备注
     */
    private String remark;

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


