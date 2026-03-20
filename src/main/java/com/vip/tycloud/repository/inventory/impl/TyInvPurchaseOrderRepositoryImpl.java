package com.vip.tycloud.repository.inventory.impl;

import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.inventory.TyInvPurchaseOrder;
import com.vip.tycloud.mapper.inventory.TyInvPurchaseOrderMapper;
import com.vip.tycloud.repository.inventory.TyInvPurchaseOrderRepository;
import org.springframework.stereotype.Repository;

/**
 * 耗材与库存 功能模块 - 采购单 - 仓储实现类。
 */
@Repository
public class TyInvPurchaseOrderRepositoryImpl extends BaseRepositoryImpl<TyInvPurchaseOrderMapper, TyInvPurchaseOrder> implements TyInvPurchaseOrderRepository {

    public TyInvPurchaseOrderRepositoryImpl(TyInvPurchaseOrderMapper baseMapper) {
        super(baseMapper);
    }
}


