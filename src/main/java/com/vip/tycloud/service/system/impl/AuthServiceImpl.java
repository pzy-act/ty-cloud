package com.vip.tycloud.service.system.impl;

import com.vip.tycloud.dto.system.CurrentUserMenuRespDTO;
import com.vip.tycloud.dto.system.CurrentUserRespDTO;
import com.vip.tycloud.dto.system.LoginReqDTO;
import com.vip.tycloud.dto.system.LoginRespDTO;
import com.vip.tycloud.entity.system.TySysMenu;
import com.vip.tycloud.repository.system.TySysUserRepository;
import com.vip.tycloud.security.CustomUserDetailsService;
import com.vip.tycloud.security.JwtTokenProvider;
import com.vip.tycloud.security.LoginUser;
import com.vip.tycloud.service.system.AuthService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        respDTO.setMenus(buildMenuTree(tySysUserRepository.listMenusByUserId(loginUser.getUserId())));
        return respDTO;
    }

    private List<CurrentUserMenuRespDTO> buildMenuTree(List<TySysMenu> menus) {
        if (CollectionUtils.isEmpty(menus)) {
            return Collections.emptyList();
        }

        Map<Long, CurrentUserMenuRespDTO> menuMap = new HashMap<>();
        List<CurrentUserMenuRespDTO> roots = new ArrayList<>();
        for (TySysMenu menu : menus) {
            if (Objects.isNull(menu) || Objects.isNull(menu.getId())) {
                continue;
            }
            menuMap.put(menu.getId(), toCurrentUserMenuResp(menu));
        }

        for (CurrentUserMenuRespDTO menu : menuMap.values()) {
            Long parentId = menu.getParentId();
            CurrentUserMenuRespDTO parent = Objects.nonNull(parentId) ? menuMap.get(parentId) : null;
            if (Objects.nonNull(parent)) {
                parent.getChildren().add(menu);
            } else {
                roots.add(menu);
            }
        }

        sortMenus(roots);
        return roots;
    }

    private void sortMenus(List<CurrentUserMenuRespDTO> menus) {
        if (CollectionUtils.isEmpty(menus)) {
            return;
        }

        menus.sort(Comparator
            .comparing((CurrentUserMenuRespDTO item) -> Objects.isNull(item.getSortNo()) ? 0 : item.getSortNo())
            .thenComparing(item -> Objects.isNull(item.getId()) ? 0L : item.getId()));
        for (CurrentUserMenuRespDTO menu : menus) {
            sortMenus(menu.getChildren());
        }
    }

    private CurrentUserMenuRespDTO toCurrentUserMenuResp(TySysMenu menu) {
        CurrentUserMenuRespDTO respDTO = new CurrentUserMenuRespDTO();
        respDTO.setId(menu.getId());
        respDTO.setParentId(menu.getParentId());
        respDTO.setMenuName(menu.getMenuName());
        respDTO.setMenuType(menu.getMenuType());
        respDTO.setRoutePath(menu.getRoutePath());
        respDTO.setComponent(menu.getComponent());
        respDTO.setPerms(menu.getPerms());
        respDTO.setIcon(menu.getIcon());
        respDTO.setSortNo(menu.getSortNo());
        respDTO.setChildren(new ArrayList<>());
        return respDTO;
    }
}
