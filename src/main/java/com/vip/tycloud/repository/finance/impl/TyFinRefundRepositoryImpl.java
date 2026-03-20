package com.vip.tycloud.repository.finance.impl;

import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.finance.TyFinRefund;
import com.vip.tycloud.mapper.finance.TyFinRefundMapper;
import com.vip.tycloud.repository.finance.TyFinRefundRepository;
import org.springframework.stereotype.Repository;

/**
 * 收费与订单 功能模块 - 退款记录 - 仓储实现类。
 */
@Repository
public class TyFinRefundRepositoryImpl extends BaseRepositoryImpl<TyFinRefundMapper, TyFinRefund> implements TyFinRefundRepository {

    public TyFinRefundRepositoryImpl(TyFinRefundMapper baseMapper) {
        super(baseMapper);
    }
}


