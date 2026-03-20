package com.vip.tycloud.repository.finance.impl;

import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.finance.TyFinPayment;
import com.vip.tycloud.mapper.finance.TyFinPaymentMapper;
import com.vip.tycloud.repository.finance.TyFinPaymentRepository;
import org.springframework.stereotype.Repository;

/**
 * 收费与订单 功能模块 - 收款记录 - 仓储实现类。
 */
@Repository
public class TyFinPaymentRepositoryImpl extends BaseRepositoryImpl<TyFinPaymentMapper, TyFinPayment> implements TyFinPaymentRepository {

    public TyFinPaymentRepositoryImpl(TyFinPaymentMapper baseMapper) {
        super(baseMapper);
    }
}


