package com.vip.tycloud.service.system.impl;

import com.vip.tycloud.dto.system.CurrentUserRespDTO;
import com.vip.tycloud.dto.system.LoginReqDTO;
import com.vip.tycloud.dto.system.LoginRespDTO;
import com.vip.tycloud.repository.system.TySysUserRepository;
import com.vip.tycloud.security.CustomUserDetailsService;
import com.vip.tycloud.security.JwtTokenProvider;
import com.vip.tycloud.security.LoginUser;
import com.vip.tycloud.service.system.AuthService;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * 组织与权限 功能模块 - 登录认证 - 服务实现类。
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final CustomUserDetailsService customUserDetailsService;

    private final JwtTokenProvider jwtTokenProvider;

    private final TySysUserRepository tySysUserRepository;

    @Override
    public LoginRespDTO login(LoginReqDTO req) {
        if (Objects.isNull(req) || !StringUtils.hasText(req.getUsername()) || !StringUtils.hasText(req.getPassword())) {
            return null;
        }

        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
            );
            if (!(authentication.getPrincipal() instanceof LoginUser)) {
                return null;
            }

            LoginUser loginUser = (LoginUser) authentication.getPrincipal();
            boolean rememberMe = Boolean.TRUE.equals(req.getRememberMe());
            tySysUserRepository.updateLastLoginTime(loginUser.getUserId(), LocalDateTime.now());
            return buildLoginResp(loginUser, rememberMe);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public LoginRespDTO refreshToken(String refreshToken) {
        if (!StringUtils.hasText(refreshToken)
            || !jwtTokenProvider.validateToken(refreshToken)
            || !jwtTokenProvider.isRefreshToken(refreshToken)) {
            return null;
        }

        String username = jwtTokenProvider.getUsername(refreshToken);
        if (!StringUtils.hasText(username)) {
            return null;
        }

        try {
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
            if (!(userDetails instanceof LoginUser)) {
                return null;
            }
            LoginUser loginUser = (LoginUser) userDetails;
            if (!loginUser.isEnabled()) {
                return null;
            }

            boolean rememberMe = jwtTokenProvider.isRememberMe(refreshToken);
            return buildLoginResp(loginUser, rememberMe);
        } catch (UsernameNotFoundException ex) {
            return null;
        }
    }

    @Override
    public CurrentUserRespDTO currentUser() {
        Authentication authentication = org.springframework.security.core.context.SecurityContextHolder.getContext()
            .getAuthentication();
        if (Objects.isNull(authentication) || !authentication.isAuthenticated()) {
            return null;
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof LoginUser) {
            return toCurrentUserResp((LoginUser) principal);
        }

        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            if (StringUtils.hasText(userDetails.getUsername())) {
                return loadCurrentUserByUsername(userDetails.getUsername());
            }
        }

        if (principal instanceof String) {
            String username = (String) principal;
            if (StringUtils.hasText(username) && !"anonymousUser".equals(username)) {
                return loadCurrentUserByUsername(username);
            }
        }

        return null;
    }

    @Override
    public boolean logout() {
        org.springframework.security.core.context.SecurityContextHolder.clearContext();
        return true;
    }

    private LoginRespDTO buildLoginResp(LoginUser loginUser, boolean rememberMe) {
        String accessToken = jwtTokenProvider.createAccessToken(loginUser);
        String refreshToken = jwtTokenProvider.createRefreshToken(loginUser, rememberMe);

        LoginRespDTO respDTO = new LoginRespDTO();
        respDTO.setAccessToken(accessToken);
        respDTO.setRefreshToken(refreshToken);
        respDTO.setTokenType("Bearer");
        respDTO.setAccessTokenExpireSeconds(jwtTokenProvider.getAccessTokenExpireSeconds());
        respDTO.setRefreshTokenExpireSeconds(jwtTokenProvider.getRefreshTokenExpireSeconds(rememberMe));
        respDTO.setUserInfo(toCurrentUserResp(loginUser));
        return respDTO;
    }

    private CurrentUserRespDTO loadCurrentUserByUsername(String username) {
        try {
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
            if (userDetails instanceof LoginUser) {
                return toCurrentUserResp((LoginUser) userDetails);
            }
        } catch (UsernameNotFoundException ex) {
            return null;
        }
        return null;
    }

    private CurrentUserRespDTO toCurrentUserResp(LoginUser loginUser) {
        CurrentUserRespDTO respDTO = new CurrentUserRespDTO();
        respDTO.setUserId(loginUser.getUserId());
        respDTO.setCampusId(loginUser.getCampusId());
        respDTO.setUsername(loginUser.getUsername());
        respDTO.setRealName(loginUser.getRealName());
        List<String> roleCodes = loginUser.getRoleCodes();
        List<String> permissions = loginUser.getPermissions();
        respDTO.setRoleCodes(CollectionUtils.isEmpty(roleCodes) ? Collections.emptyList() : roleCodes);
        respDTO.setPermissions(CollectionUtils.isEmpty(permissions) ? Collections.emptyList() : permissions);
        return respDTO;
    }
}
