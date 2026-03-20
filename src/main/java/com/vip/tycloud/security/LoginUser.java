package com.vip.tycloud.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * 认证授权 - 登录用户信息。
 */
@Getter
@Builder
public class LoginUser implements UserDetails, Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID。
     */
    private final Long userId;

    /**
     * 校区ID。
     */
    private final Long campusId;

    /**
     * 用户名。
     */
    private final String username;

    /**
     * 密码哈希。
     */
    private final String password;

    /**
     * 真实姓名。
     */
    private final String realName;

    /**
     * 用户状态。
     */
    private final Integer status;

    /**
     * 角色编码列表。
     */
    private final List<String> roleCodes;

    /**
     * 权限标识列表。
     */
    private final List<String> permissions;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if (!CollectionUtils.isEmpty(permissions)) {
            for (String permission : permissions) {
                if (StringUtils.hasText(permission)) {
                    authorities.add(new SimpleGrantedAuthority(permission));
                }
            }
        }

        if (!CollectionUtils.isEmpty(roleCodes)) {
            for (String roleCode : roleCodes) {
                if (StringUtils.hasText(roleCode)) {
                    authorities.add(new SimpleGrantedAuthority("ROLE_" + roleCode));
                }
            }
        }

        return Collections.unmodifiableList(authorities);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return Objects.nonNull(status) && status == 1;
    }
}
