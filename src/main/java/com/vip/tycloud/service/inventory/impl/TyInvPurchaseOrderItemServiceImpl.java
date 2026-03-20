package com.vip.tycloud.service.inventory.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.inventory.TyInvPurchaseOrderItem;
import com.vip.tycloud.repository.inventory.TyInvPurchaseOrderItemRepository;
import com.vip.tycloud.service.inventory.TyInvPurchaseOrderItemService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 耗材与库存 功能模块 - 采购明细 - 服务实现类。
 */
@Service
@RequiredArgsConstructor
public class TyInvPurchaseOrderItemServiceImpl implements TyInvPurchaseOrderItemService {

    private final TyInvPurchaseOrderItemRepository tyInvPurchaseOrderItemRepository;

    @Override
    public TyInvPurchaseOrderItem getById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return tyInvPurchaseOrderItemRepository.getById(id);
    }

    @Override
    public PageResultDTO<TyInvPurchaseOrderItem> page(Integer pageNumber, Integer pageSize) {
        long current = Objects.isNull(pageNumber) || pageNumber < 1 ? 1L : pageNumber;
        long size = Objects.isNull(pageSize) || pageSize < 1 ? 10L : pageSize;
        Page<TyInvPurchaseOrderItem> page = new Page<>(current, size);
        IPage<TyInvPurchaseOrderItem> pageResult = tyInvPurchaseOrderItemRepository.page(
            page,
            Wrappers.<TyInvPurchaseOrderItem>lambdaQuery()
                .eq(TyInvPurchaseOrderItem::getIsDeleted, 0)
                .orderByDesc(TyInvPurchaseOrderItem::getId)
        );
        return PageResultDTO.of(pageResult.getTotal(), pageResult.getRecords());
    }

    @Override
    public boolean save(TyInvPurchaseOrderItem entity) {
        if (Objects.isNull(entity)) {
            return false;
        }
        entity.setId(null);
        if (Objects.isNull(entity.getIsDeleted())) {
            entity.setIsDeleted(0);
        }
        return tyInvPurchaseOrderItemRepository.save(entity) > 0;
    }

    @Override
    public boolean updateById(TyInvPurchaseOrderItem entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId())) {
            return false;
        }
        return tyInvPurchaseOrderItemRepository.updateById(entity) > 0;
    }

    @Override
    public boolean deleteById(Long id, Long operatorId) {
        if (Objects.isNull(id)) {
            return false;
        }
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return tyInvPurchaseOrderItemRepository.logicDeleteById(id, actualOperatorId) > 0;
    }
}


