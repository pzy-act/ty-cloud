package com.vip.tycloud.service.system.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.system.TySysUserRole;
import com.vip.tycloud.repository.system.TySysUserRoleRepository;
import com.vip.tycloud.service.system.TySysUserRoleService;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
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
    public List<Long> listRoleIdsByUserId(Long userId) {
        if (Objects.isNull(userId)) {
            return Collections.emptyList();
        }
        List<TySysUserRole> userRoles = tySysUserRoleRepository.list(
            Wrappers.<TySysUserRole>lambdaQuery()
                .eq(TySysUserRole::getUserId, userId)
                .eq(TySysUserRole::getIsDeleted, 0)
        );
        return userRoles.stream()
            .map(TySysUserRole::getRoleId)
            .filter(Objects::nonNull)
            .distinct()
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean assignRoles(Long userId, List<Long> roleIds, Long operatorId) {
        if (Objects.isNull(userId)) {
            return false;
        }
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        LocalDateTime now = LocalDateTime.now();
        List<TySysUserRole> oldRelations = tySysUserRoleRepository.list(
            Wrappers.<TySysUserRole>lambdaQuery()
                .eq(TySysUserRole::getUserId, userId)
                .eq(TySysUserRole::getIsDeleted, 0)
        );
        for (TySysUserRole relation : oldRelations) {
            relation.setIsDeleted(1);
            relation.setUpdatedBy(actualOperatorId);
            relation.setUpdatedTime(now);
            tySysUserRoleRepository.updateById(relation);
        }
        if (CollectionUtils.isEmpty(roleIds)) {
            return true;
        }
        List<Long> distinctRoleIds = roleIds.stream()
            .filter(Objects::nonNull)
            .distinct()
            .collect(Collectors.toList());
        for (Long roleId : distinctRoleIds) {
            TySysUserRole relation = new TySysUserRole();
            relation.setUserId(userId);
            relation.setRoleId(roleId);
            relation.setIsDeleted(0);
            relation.setCreatedBy(actualOperatorId);
            relation.setCreatedTime(now);
            relation.setUpdatedBy(actualOperatorId);
            relation.setUpdatedTime(now);
            tySysUserRoleRepository.save(relation);
        }
        return true;
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


