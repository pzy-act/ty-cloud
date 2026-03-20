package com.vip.tycloud.repository.system.impl;

import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.system.TySysMenu;
import com.vip.tycloud.mapper.system.TySysMenuMapper;
import com.vip.tycloud.repository.system.TySysMenuRepository;
import org.springframework.stereotype.Repository;

/**
 * 组织与权限 功能模块 - 菜单权限 - 仓储实现类。
 */
@Repository
public class TySysMenuRepositoryImpl extends BaseRepositoryImpl<TySysMenuMapper, TySysMenu> implements TySysMenuRepository {

    public TySysMenuRepositoryImpl(TySysMenuMapper baseMapper) {
        super(baseMapper);
    }
}


