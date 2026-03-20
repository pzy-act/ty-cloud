package com.vip.tycloud.service.inventory.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.inventory.TyInvMaterial;
import com.vip.tycloud.repository.inventory.TyInvMaterialRepository;
import com.vip.tycloud.service.inventory.TyInvMaterialService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 耗材与库存 功能模块 - 耗材档案 - 服务实现类。
 */
@Service
@RequiredArgsConstructor
public class TyInvMaterialServiceImpl implements TyInvMaterialService {

    private final TyInvMaterialRepository tyInvMaterialRepository;

    @Override
    public TyInvMaterial getById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return tyInvMaterialRepository.getById(id);
    }

    @Override
    public PageResultDTO<TyInvMaterial> page(Integer pageNumber, Integer pageSize) {
        long current = Objects.isNull(pageNumber) || pageNumber < 1 ? 1L : pageNumber;
        long size = Objects.isNull(pageSize) || pageSize < 1 ? 10L : pageSize;
        Page<TyInvMaterial> page = new Page<>(current, size);
        IPage<TyInvMaterial> pageResult = tyInvMaterialRepository.page(
            page,
            Wrappers.<TyInvMaterial>lambdaQuery()
                .eq(TyInvMaterial::getIsDeleted, 0)
                .orderByDesc(TyInvMaterial::getId)
        );
        return PageResultDTO.of(pageResult.getTotal(), pageResult.getRecords());
    }

    @Override
    public boolean save(TyInvMaterial entity) {
        if (Objects.isNull(entity)) {
            return false;
        }
        entity.setId(null);
        if (Objects.isNull(entity.getIsDeleted())) {
            entity.setIsDeleted(0);
        }
        return tyInvMaterialRepository.save(entity) > 0;
    }

    @Override
    public boolean updateById(TyInvMaterial entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId())) {
            return false;
        }
        return tyInvMaterialRepository.updateById(entity) > 0;
    }

    @Override
    public boolean deleteById(Long id, Long operatorId) {
        if (Objects.isNull(id)) {
            return false;
        }
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return tyInvMaterialRepository.logicDeleteById(id, actualOperatorId) > 0;
    }
}


