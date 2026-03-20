package com.vip.tycloud.repository.system.impl;

import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.system.TySysRoleMenu;
import com.vip.tycloud.mapper.system.TySysRoleMenuMapper;
import com.vip.tycloud.repository.system.TySysRoleMenuRepository;
import org.springframework.stereotype.Repository;

/**
 * 组织与权限 功能模块 - 角色菜单关联 - 仓储实现类。
 */
@Repository
public class TySysRoleMenuRepositoryImpl extends BaseRepositoryImpl<TySysRoleMenuMapper, TySysRoleMenu> implements TySysRoleMenuRepository {

    public TySysRoleMenuRepositoryImpl(TySysRoleMenuMapper baseMapper) {
        super(baseMapper);
    }
}


