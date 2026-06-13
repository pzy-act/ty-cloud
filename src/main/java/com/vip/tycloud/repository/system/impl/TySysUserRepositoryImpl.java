package com.vip.tycloud.repository.system.impl;

import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.system.TySysMenu;
import com.vip.tycloud.entity.system.TySysUser;
import com.vip.tycloud.mapper.system.TySysUserMapper;
import com.vip.tycloud.repository.system.TySysUserRepository;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

/**
 * 组织与权限 功能模块 - 用户管理 - 仓储实现类。
 */
@Repository
public class TySysUserRepositoryImpl extends BaseRepositoryImpl<TySysUserMapper, TySysUser> implements TySysUserRepository {

    public TySysUserRepositoryImpl(TySysUserMapper baseMapper) {
        super(baseMapper);
    }

    @Override
    public TySysUser findByUsername(String username) {
        if (!StringUtils.hasText(username)) {
            return null;
        }
        return baseMapper.selectByUsername(username);
    }

    @Override
    public List<String> listRoleCodesByUserId(Long userId) {
        if (Objects.isNull(userId)) {
            return Collections.emptyList();
        }
        List<String> roleCodes = baseMapper.selectRoleCodesByUserId(userId);
        return Objects.isNull(roleCodes) ? Collections.emptyList() : roleCodes;
    }

    @Override
    public List<String> listPermsByUserId(Long userId) {
        if (Objects.isNull(userId)) {
            return Collections.emptyList();
        }
        List<String> perms = baseMapper.selectPermsByUserId(userId);
        return Objects.isNull(perms) ? Collections.emptyList() : perms;
    }

    @Override
    public List<TySysMenu> listMenusByUserId(Long userId) {
        if (Objects.isNull(userId)) {
            return Collections.emptyList();
        }
        List<TySysMenu> menus = baseMapper.selectMenusByUserId(userId);
        return Objects.isNull(menus) ? Collections.emptyList() : menus;
    }

    @Override
    public boolean updateLastLoginTime(Long userId, LocalDateTime lastLoginTime) {
        if (Objects.isNull(userId) || Objects.isNull(lastLoginTime)) {
            return false;
        }
        return baseMapper.updateLastLoginTime(userId, lastLoginTime) > 0;
    }
}
