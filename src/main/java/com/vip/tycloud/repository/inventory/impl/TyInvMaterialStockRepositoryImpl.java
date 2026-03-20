package com.vip.tycloud.repository.inventory.impl;

import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.inventory.TyInvMaterialStock;
import com.vip.tycloud.mapper.inventory.TyInvMaterialStockMapper;
import com.vip.tycloud.repository.inventory.TyInvMaterialStockRepository;
import org.springframework.stereotype.Repository;

/**
 * 耗材与库存 功能模块 - 库存台账 - 仓储实现类。
 */
@Repository
public class TyInvMaterialStockRepositoryImpl extends BaseRepositoryImpl<TyInvMaterialStockMapper, TyInvMaterialStock> implements TyInvMaterialStockRepository {

    public TyInvMaterialStockRepositoryImpl(TyInvMaterialStockMapper baseMapper) {
        super(baseMapper);
    }
}


