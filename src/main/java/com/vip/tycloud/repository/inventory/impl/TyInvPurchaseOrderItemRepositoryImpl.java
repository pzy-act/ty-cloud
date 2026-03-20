package com.vip.tycloud.repository.inventory.impl;

import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.inventory.TyInvPurchaseOrderItem;
import com.vip.tycloud.mapper.inventory.TyInvPurchaseOrderItemMapper;
import com.vip.tycloud.repository.inventory.TyInvPurchaseOrderItemRepository;
import org.springframework.stereotype.Repository;

/**
 * 耗材与库存 功能模块 - 采购明细 - 仓储实现类。
 */
@Repository
public class TyInvPurchaseOrderItemRepositoryImpl extends BaseRepositoryImpl<TyInvPurchaseOrderItemMapper, TyInvPurchaseOrderItem> implements TyInvPurchaseOrderItemRepository {

    public TyInvPurchaseOrderItemRepositoryImpl(TyInvPurchaseOrderItemMapper baseMapper) {
        super(baseMapper);
    }
}


