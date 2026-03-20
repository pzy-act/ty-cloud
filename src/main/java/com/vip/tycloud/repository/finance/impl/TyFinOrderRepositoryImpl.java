package com.vip.tycloud.repository.finance.impl;

import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.finance.TyFinOrder;
import com.vip.tycloud.mapper.finance.TyFinOrderMapper;
import com.vip.tycloud.repository.finance.TyFinOrderRepository;
import org.springframework.stereotype.Repository;

/**
 * 收费与订单 功能模块 - 收费订单 - 仓储实现类。
 */
@Repository
public class TyFinOrderRepositoryImpl extends BaseRepositoryImpl<TyFinOrderMapper, TyFinOrder> implements TyFinOrderRepository {

    public TyFinOrderRepositoryImpl(TyFinOrderMapper baseMapper) {
        super(baseMapper);
    }
}


