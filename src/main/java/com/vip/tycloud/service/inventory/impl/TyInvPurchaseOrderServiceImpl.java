package com.vip.tycloud.service.inventory.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.inventory.TyInvPurchaseOrder;
import com.vip.tycloud.repository.inventory.TyInvPurchaseOrderRepository;
import com.vip.tycloud.service.inventory.TyInvPurchaseOrderService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 耗材与库存 功能模块 - 采购单 - 服务实现类。
 */
@Service
@RequiredArgsConstructor
public class TyInvPurchaseOrderServiceImpl implements TyInvPurchaseOrderService {

    private final TyInvPurchaseOrderRepository tyInvPurchaseOrderRepository;

    @Override
    public TyInvPurchaseOrder getById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return tyInvPurchaseOrderRepository.getById(id);
    }

    @Override
    public PageResultDTO<TyInvPurchaseOrder> page(Integer pageNumber, Integer pageSize) {
        long current = Objects.isNull(pageNumber) || pageNumber < 1 ? 1L : pageNumber;
        long size = Objects.isNull(pageSize) || pageSize < 1 ? 10L : pageSize;
        Page<TyInvPurchaseOrder> page = new Page<>(current, size);
        IPage<TyInvPurchaseOrder> pageResult = tyInvPurchaseOrderRepository.page(
            page,
            Wrappers.<TyInvPurchaseOrder>lambdaQuery()
                .eq(TyInvPurchaseOrder::getIsDeleted, 0)
                .orderByDesc(TyInvPurchaseOrder::getId)
        );
        return PageResultDTO.of(pageResult.getTotal(), pageResult.getRecords());
    }

    @Override
    public boolean save(TyInvPurchaseOrder entity) {
        if (Objects.isNull(entity)) {
            return false;
        }
        entity.setId(null);
        if (Objects.isNull(entity.getIsDeleted())) {
            entity.setIsDeleted(0);
        }
        return tyInvPurchaseOrderRepository.save(entity) > 0;
    }

    @Override
    public boolean updateById(TyInvPurchaseOrder entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId())) {
            return false;
        }
        return tyInvPurchaseOrderRepository.updateById(entity) > 0;
    }

    @Override
    public boolean deleteById(Long id, Long operatorId) {
        if (Objects.isNull(id)) {
            return false;
        }
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return tyInvPurchaseOrderRepository.logicDeleteById(id, actualOperatorId) > 0;
    }
}


