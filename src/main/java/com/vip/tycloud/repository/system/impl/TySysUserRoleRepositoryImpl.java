package com.vip.tycloud.repository.system.impl;

import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.system.TySysUserRole;
import com.vip.tycloud.mapper.system.TySysUserRoleMapper;
import com.vip.tycloud.repository.system.TySysUserRoleRepository;
import org.springframework.stereotype.Repository;

/**
 * 组织与权限 功能模块 - 用户角色关联 - 仓储实现类。
 */
@Repository
public class TySysUserRoleRepositoryImpl extends BaseRepositoryImpl<TySysUserRoleMapper, TySysUserRole> implements TySysUserRoleRepository {

    public TySysUserRoleRepositoryImpl(TySysUserRoleMapper baseMapper) {
        super(baseMapper);
    }
}


