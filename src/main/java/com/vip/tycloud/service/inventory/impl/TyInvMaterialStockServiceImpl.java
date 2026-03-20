package com.vip.tycloud.service.inventory.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.inventory.TyInvMaterialStock;
import com.vip.tycloud.repository.inventory.TyInvMaterialStockRepository;
import com.vip.tycloud.service.inventory.TyInvMaterialStockService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 耗材与库存 功能模块 - 库存台账 - 服务实现类。
 */
@Service
@RequiredArgsConstructor
public class TyInvMaterialStockServiceImpl implements TyInvMaterialStockService {

    private final TyInvMaterialStockRepository tyInvMaterialStockRepository;

    @Override
    public TyInvMaterialStock getById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return tyInvMaterialStockRepository.getById(id);
    }

    @Override
    public PageResultDTO<TyInvMaterialStock> page(Integer pageNumber, Integer pageSize) {
        long current = Objects.isNull(pageNumber) || pageNumber < 1 ? 1L : pageNumber;
        long size = Objects.isNull(pageSize) || pageSize < 1 ? 10L : pageSize;
        Page<TyInvMaterialStock> page = new Page<>(current, size);
        IPage<TyInvMaterialStock> pageResult = tyInvMaterialStockRepository.page(
            page,
            Wrappers.<TyInvMaterialStock>lambdaQuery()
                .eq(TyInvMaterialStock::getIsDeleted, 0)
                .orderByDesc(TyInvMaterialStock::getId)
        );
        return PageResultDTO.of(pageResult.getTotal(), pageResult.getRecords());
    }

    @Override
    public boolean save(TyInvMaterialStock entity) {
        if (Objects.isNull(entity)) {
            return false;
        }
        entity.setId(null);
        if (Objects.isNull(entity.getIsDeleted())) {
            entity.setIsDeleted(0);
        }
        return tyInvMaterialStockRepository.save(entity) > 0;
    }

    @Override
    public boolean updateById(TyInvMaterialStock entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId())) {
            return false;
        }
        return tyInvMaterialStockRepository.updateById(entity) > 0;
    }

    @Override
    public boolean deleteById(Long id, Long operatorId) {
        if (Objects.isNull(id)) {
            return false;
        }
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return tyInvMaterialStockRepository.logicDeleteById(id, actualOperatorId) > 0;
    }
}


