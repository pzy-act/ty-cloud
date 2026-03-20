package com.vip.tycloud.security;

import com.vip.tycloud.entity.system.TySysUser;
import com.vip.tycloud.repository.system.TySysUserRepository;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 认证授权 - 用户信息加载服务。
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final TySysUserRepository tySysUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!StringUtils.hasText(username)) {
            throw new UsernameNotFoundException("用户名不能为空");
        }

        TySysUser user = tySysUserRepository.findByUsername(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("用户不存在");
        }

        List<String> roleCodes = tySysUserRepository.listRoleCodesByUserId(user.getId());
        List<String> permissions = tySysUserRepository.listPermsByUserId(user.getId());

        return LoginUser.builder()
            .userId(user.getId())
            .campusId(user.getCampusId())
            .username(user.getUsername())
            .password(user.getPasswordHash())
            .realName(user.getRealName())
            .status(user.getStatus())
            .roleCodes(Objects.isNull(roleCodes) ? Collections.emptyList() : roleCodes)
            .permissions(Objects.isNull(permissions) ? Collections.emptyList() : permissions)
            .build();
    }
}
