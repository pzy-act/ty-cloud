package com.vip.tycloud.repository.inventory.impl;

import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.inventory.TyInvMaterialTxn;
import com.vip.tycloud.mapper.inventory.TyInvMaterialTxnMapper;
import com.vip.tycloud.repository.inventory.TyInvMaterialTxnRepository;
import org.springframework.stereotype.Repository;

/**
 * 耗材与库存 功能模块 - 库存流水 - 仓储实现类。
 */
@Repository
public class TyInvMaterialTxnRepositoryImpl extends BaseRepositoryImpl<TyInvMaterialTxnMapper, TyInvMaterialTxn> implements TyInvMaterialTxnRepository {

    public TyInvMaterialTxnRepositoryImpl(TyInvMaterialTxnMapper baseMapper) {
        super(baseMapper);
    }
}


