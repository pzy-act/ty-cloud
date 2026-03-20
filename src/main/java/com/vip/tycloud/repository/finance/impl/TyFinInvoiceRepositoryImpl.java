package com.vip.tycloud.repository.finance.impl;

import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.finance.TyFinInvoice;
import com.vip.tycloud.mapper.finance.TyFinInvoiceMapper;
import com.vip.tycloud.repository.finance.TyFinInvoiceRepository;
import org.springframework.stereotype.Repository;

/**
 * 收费与订单 功能模块 - 发票管理 - 仓储实现类。
 */
@Repository
public class TyFinInvoiceRepositoryImpl extends BaseRepositoryImpl<TyFinInvoiceMapper, TyFinInvoice> implements TyFinInvoiceRepository {

    public TyFinInvoiceRepositoryImpl(TyFinInvoiceMapper baseMapper) {
        super(baseMapper);
    }
}


