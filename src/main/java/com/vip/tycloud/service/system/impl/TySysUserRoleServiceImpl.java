package com.vip.tycloud.service.system.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.system.TySysUserRole;
import com.vip.tycloud.repository.system.TySysUserRoleRepository;
import com.vip.tycloud.service.system.TySysUserRoleService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 组织与权限 功能模块 - 用户角色关联 - 服务实现类。
 */
@Service
@RequiredArgsConstructor
public class TySysUserRoleServiceImpl implements TySysUserRoleService {

    private final TySysUserRoleRepository tySysUserRoleRepository;

    @Override
    public TySysUserRole getById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return tySysUserRoleRepository.getById(id);
    }

    @Override
    public PageResultDTO<TySysUserRole> page(Integer pageNumber, Integer pageSize) {
        long current = Objects.isNull(pageNumber) || pageNumber < 1 ? 1L : pageNumber;
        long size = Objects.isNull(pageSize) || pageSize < 1 ? 10L : pageSize;
        Page<TySysUserRole> page = new Page<>(current, size);
        IPage<TySysUserRole> pageResult = tySysUserRoleRepository.page(
            page,
            Wrappers.<TySysUserRole>lambdaQuery()
                .eq(TySysUserRole::getIsDeleted, 0)
                .orderByDesc(TySysUserRole::getId)
        );
        return PageResultDTO.of(pageResult.getTotal(), pageResult.getRecords());
    }

    @Override
    public boolean save(TySysUserRole entity) {
        if (Objects.isNull(entity)) {
            return false;
        }
        entity.setId(null);
        if (Objects.isNull(entity.getIsDeleted())) {
            entity.setIsDeleted(0);
        }
        return tySysUserRoleRepository.save(entity) > 0;
    }

    @Override
    public boolean updateById(TySysUserRole entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId())) {
            return false;
        }
        return tySysUserRoleRepository.updateById(entity) > 0;
    }

    @Override
    public boolean deleteById(Long id, Long operatorId) {
        if (Objects.isNull(id)) {
            return false;
        }
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return tySysUserRoleRepository.logicDeleteById(id, actualOperatorId) > 0;
    }
}


