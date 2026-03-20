package com.vip.tycloud.repository.contract.impl;

import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.contract.TyBizContractItem;
import com.vip.tycloud.mapper.contract.TyBizContractItemMapper;
import com.vip.tycloud.repository.contract.TyBizContractItemRepository;
import org.springframework.stereotype.Repository;

/**
 * 报名与合同 功能模块 - 合同明细 - 仓储实现类。
 */
@Repository
public class TyBizContractItemRepositoryImpl extends BaseRepositoryImpl<TyBizContractItemMapper, TyBizContractItem> implements TyBizContractItemRepository {

    public TyBizContractItemRepositoryImpl(TyBizContractItemMapper baseMapper) {
        super(baseMapper);
    }
}


