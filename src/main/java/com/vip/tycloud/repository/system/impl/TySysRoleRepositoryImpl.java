package com.vip.tycloud.repository.system.impl;

import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.system.TySysRole;
import com.vip.tycloud.mapper.system.TySysRoleMapper;
import com.vip.tycloud.repository.system.TySysRoleRepository;
import org.springframework.stereotype.Repository;

/**
 * 组织与权限 功能模块 - 角色管理 - 仓储实现类。
 */
@Repository
public class TySysRoleRepositoryImpl extends BaseRepositoryImpl<TySysRoleMapper, TySysRole> implements TySysRoleRepository {

    public TySysRoleRepositoryImpl(TySysRoleMapper baseMapper) {
        super(baseMapper);
    }
}


