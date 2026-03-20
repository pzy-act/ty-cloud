package com.vip.tycloud.service.system.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.system.TySysMenu;
import com.vip.tycloud.repository.system.TySysMenuRepository;
import com.vip.tycloud.service.system.TySysMenuService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 组织与权限 功能模块 - 菜单权限 - 服务实现类。
 */
@Service
@RequiredArgsConstructor
public class TySysMenuServiceImpl implements TySysMenuService {

    private final TySysMenuRepository tySysMenuRepository;

    @Override
    public TySysMenu getById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return tySysMenuRepository.getById(id);
    }

    @Override
    public PageResultDTO<TySysMenu> page(Integer pageNumber, Integer pageSize) {
        long current = Objects.isNull(pageNumber) || pageNumber < 1 ? 1L : pageNumber;
        long size = Objects.isNull(pageSize) || pageSize < 1 ? 10L : pageSize;
        Page<TySysMenu> page = new Page<>(current, size);
        IPage<TySysMenu> pageResult = tySysMenuRepository.page(
            page,
            Wrappers.<TySysMenu>lambdaQuery()
                .eq(TySysMenu::getIsDeleted, 0)
                .orderByDesc(TySysMenu::getId)
        );
        return PageResultDTO.of(pageResult.getTotal(), pageResult.getRecords());
    }

    @Override
    public boolean save(TySysMenu entity) {
        if (Objects.isNull(entity)) {
            return false;
        }
        entity.setId(null);
        if (Objects.isNull(entity.getIsDeleted())) {
            entity.setIsDeleted(0);
        }
        return tySysMenuRepository.save(entity) > 0;
    }

    @Override
    public boolean updateById(TySysMenu entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId())) {
            return false;
        }
        return tySysMenuRepository.updateById(entity) > 0;
    }

    @Override
    public boolean deleteById(Long id, Long operatorId) {
        if (Objects.isNull(id)) {
            return false;
        }
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return tySysMenuRepository.logicDeleteById(id, actualOperatorId) > 0;
    }
}


