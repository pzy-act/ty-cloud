package com.vip.tycloud.service.system.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.system.TySysRole;
import com.vip.tycloud.repository.system.TySysRoleRepository;
import com.vip.tycloud.service.system.TySysRoleService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 组织与权限 功能模块 - 角色管理 - 服务实现类。
 */
@Service
@RequiredArgsConstructor
public class TySysRoleServiceImpl implements TySysRoleService {

    private final TySysRoleRepository tySysRoleRepository;

    @Override
    public TySysRole getById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return tySysRoleRepository.getById(id);
    }

    @Override
    public PageResultDTO<TySysRole> page(Integer pageNumber, Integer pageSize) {
        long current = Objects.isNull(pageNumber) || pageNumber < 1 ? 1L : pageNumber;
        long size = Objects.isNull(pageSize) || pageSize < 1 ? 10L : pageSize;
        Page<TySysRole> page = new Page<>(current, size);
        IPage<TySysRole> pageResult = tySysRoleRepository.page(
            page,
            Wrappers.<TySysRole>lambdaQuery()
                .eq(TySysRole::getIsDeleted, 0)
                .orderByDesc(TySysRole::getId)
        );
        return PageResultDTO.of(pageResult.getTotal(), pageResult.getRecords());
    }

    @Override
    public boolean save(TySysRole entity) {
        if (Objects.isNull(entity)) {
            return false;
        }
        entity.setId(null);
        if (Objects.isNull(entity.getIsDeleted())) {
            entity.setIsDeleted(0);
        }
        return tySysRoleRepository.save(entity) > 0;
    }

    @Override
    public boolean updateById(TySysRole entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId())) {
            return false;
        }
        return tySysRoleRepository.updateById(entity) > 0;
    }

    @Override
    public boolean deleteById(Long id, Long operatorId) {
        if (Objects.isNull(id)) {
            return false;
        }
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return tySysRoleRepository.logicDeleteById(id, actualOperatorId) > 0;
    }
}


