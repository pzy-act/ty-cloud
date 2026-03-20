package com.vip.tycloud.service.system.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.system.TySysRoleMenu;
import com.vip.tycloud.repository.system.TySysRoleMenuRepository;
import com.vip.tycloud.service.system.TySysRoleMenuService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 组织与权限 功能模块 - 角色菜单关联 - 服务实现类。
 */
@Service
@RequiredArgsConstructor
public class TySysRoleMenuServiceImpl implements TySysRoleMenuService {

    private final TySysRoleMenuRepository tySysRoleMenuRepository;

    @Override
    public TySysRoleMenu getById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return tySysRoleMenuRepository.getById(id);
    }

    @Override
    public PageResultDTO<TySysRoleMenu> page(Integer pageNumber, Integer pageSize) {
        long current = Objects.isNull(pageNumber) || pageNumber < 1 ? 1L : pageNumber;
        long size = Objects.isNull(pageSize) || pageSize < 1 ? 10L : pageSize;
        Page<TySysRoleMenu> page = new Page<>(current, size);
        IPage<TySysRoleMenu> pageResult = tySysRoleMenuRepository.page(
            page,
            Wrappers.<TySysRoleMenu>lambdaQuery()
                .eq(TySysRoleMenu::getIsDeleted, 0)
                .orderByDesc(TySysRoleMenu::getId)
        );
        return PageResultDTO.of(pageResult.getTotal(), pageResult.getRecords());
    }

    @Override
    public boolean save(TySysRoleMenu entity) {
        if (Objects.isNull(entity)) {
            return false;
        }
        entity.setId(null);
        if (Objects.isNull(entity.getIsDeleted())) {
            entity.setIsDeleted(0);
        }
        return tySysRoleMenuRepository.save(entity) > 0;
    }

    @Override
    public boolean updateById(TySysRoleMenu entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId())) {
            return false;
        }
        return tySysRoleMenuRepository.updateById(entity) > 0;
    }

    @Override
    public boolean deleteById(Long id, Long operatorId) {
        if (Objects.isNull(id)) {
            return false;
        }
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return tySysRoleMenuRepository.logicDeleteById(id, actualOperatorId) > 0;
    }
}


