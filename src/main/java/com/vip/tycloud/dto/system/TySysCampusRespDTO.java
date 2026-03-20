package com.vip.tycloud.dto.system;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * 组织与权限 功能模块 - 校区管理 - 响应 DTO。
 */
@Data
public class TySysCampusRespDTO {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 校区编码
     */
    private String campusCode;

    /**
     * 校区名称
     */
    private String campusName;

    /**
     * 联系人姓名
     */
    private String contactName;

    /**
     * 联系电话
     */
    private String contactPhone;

    /**
     * 地址
     */
    private String address;

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


