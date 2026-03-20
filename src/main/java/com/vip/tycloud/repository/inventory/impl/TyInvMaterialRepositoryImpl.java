package com.vip.tycloud.repository.inventory.impl;

import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.inventory.TyInvMaterial;
import com.vip.tycloud.mapper.inventory.TyInvMaterialMapper;
import com.vip.tycloud.repository.inventory.TyInvMaterialRepository;
import org.springframework.stereotype.Repository;

/**
 * 耗材与库存 功能模块 - 耗材档案 - 仓储实现类。
 */
@Repository
public class TyInvMaterialRepositoryImpl extends BaseRepositoryImpl<TyInvMaterialMapper, TyInvMaterial> implements TyInvMaterialRepository {

    public TyInvMaterialRepositoryImpl(TyInvMaterialMapper baseMapper) {
        super(baseMapper);
    }
}


