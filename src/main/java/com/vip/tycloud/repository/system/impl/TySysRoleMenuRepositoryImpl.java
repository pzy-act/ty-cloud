package com.vip.tycloud.repository.system.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.system.TySysRoleMenu;
import com.vip.tycloud.mapper.system.TySysRoleMenuMapper;
import com.vip.tycloud.repository.system.TySysRoleMenuRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * 组织与权限 功能模块 - 角色菜单关联 - 仓储实现类。
 */
@Repository
public class TySysRoleMenuRepositoryImpl extends BaseRepositoryImpl<TySysRoleMenuMapper, TySysRoleMenu> implements TySysRoleMenuRepository {

    public TySysRoleMenuRepositoryImpl(TySysRoleMenuMapper baseMapper) {
        super(baseMapper);
    }

    @Override
    public List<TySysRoleMenu> listByRoleId(Long roleId) {
        return baseMapper.selectList(
            Wrappers.<TySysRoleMenu>lambdaQuery()
                .eq(TySysRoleMenu::getRoleId, roleId)
                .eq(TySysRoleMenu::getIsDeleted, 0)
        );
    }

    @Override
    public int logicDeleteByRoleId(Long roleId, Long operatorId) {
        UpdateWrapper<TySysRoleMenu> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("role_id", roleId)
            .eq("is_deleted", 0)
            .set("is_deleted", 1)
            .set("updated_by", operatorId)
            .set("updated_time", LocalDateTime.now());
        return baseMapper.update(null, updateWrapper);
    }
}


