package com.vip.tycloud.dto.student;

import lombok.Data;

/**
 * 学员管理 功能模块 - 监护人管理 - 新增请求 DTO。
 */
@Data
public class TyStuGuardianCreateReqDTO {

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
}


