package com.vip.tycloud.repository.contract.impl;

import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.contract.TyBizContract;
import com.vip.tycloud.mapper.contract.TyBizContractMapper;
import com.vip.tycloud.repository.contract.TyBizContractRepository;
import org.springframework.stereotype.Repository;

/**
 * 报名与合同 功能模块 - 报名合同 - 仓储实现类。
 */
@Repository
public class TyBizContractRepositoryImpl extends BaseRepositoryImpl<TyBizContractMapper, TyBizContract> implements TyBizContractRepository {

    public TyBizContractRepositoryImpl(TyBizContractMapper baseMapper) {
        super(baseMapper);
    }
}


