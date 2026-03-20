package com.vip.tycloud.dto.system;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * 组织与权限 功能模块 - 用户管理 - 新增请求 DTO。
 */
@Data
public class TySysUserCreateReqDTO {

    /**
     * 校区ID
     */
    private Long campusId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码哈希
     */
    private String passwordHash;

    /**
     * 真实名称
     */
    private String realName;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 工号
     */
    private String jobNo;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 最近登录时间
     */
    private LocalDateTime lastLoginTime;
}


