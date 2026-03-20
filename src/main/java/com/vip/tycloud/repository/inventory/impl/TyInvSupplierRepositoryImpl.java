package com.vip.tycloud.repository.inventory.impl;

import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.inventory.TyInvSupplier;
import com.vip.tycloud.mapper.inventory.TyInvSupplierMapper;
import com.vip.tycloud.repository.inventory.TyInvSupplierRepository;
import org.springframework.stereotype.Repository;

/**
 * 耗材与库存 功能模块 - 供应商管理 - 仓储实现类。
 */
@Repository
public class TyInvSupplierRepositoryImpl extends BaseRepositoryImpl<TyInvSupplierMapper, TyInvSupplier> implements TyInvSupplierRepository {

    public TyInvSupplierRepositoryImpl(TyInvSupplierMapper baseMapper) {
        super(baseMapper);
    }
}


