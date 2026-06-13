package com.vip.tycloud.repository.system;

import com.vip.tycloud.common.repository.BaseRepository;
import com.vip.tycloud.entity.system.TySysRoleMenu;
import java.util.List;

/**
 * 组织与权限 功能模块 - 角色菜单关联 - 仓储接口。
 */
public interface TySysRoleMenuRepository extends BaseRepository<TySysRoleMenu> {

    /**
     * 按角色 ID 查询未删除的关联关系。
     *
     * @param roleId 角色 ID
     * @return 角色菜单关联列表
     */
    List<TySysRoleMenu> listByRoleId(Long roleId);

    /**
     * 按角色 ID 逻辑删除旧授权。
     *
     * @param roleId 角色 ID
     * @param operatorId 操作人 ID
     * @return 影响行数
     */
    int logicDeleteByRoleId(Long roleId, Long operatorId);
}


