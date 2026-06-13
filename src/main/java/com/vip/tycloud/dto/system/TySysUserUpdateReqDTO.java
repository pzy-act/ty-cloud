package com.vip.tycloud.dto.system;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 组织与权限 功能模块 - 用户管理 - 更新请求 DTO。
 */
@Data
public class TySysUserUpdateReqDTO {

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
     * 用户名
     */
    private String username;

    /**
     * 密码哈希
     */
    private String passwordHash;

    private String password;

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


