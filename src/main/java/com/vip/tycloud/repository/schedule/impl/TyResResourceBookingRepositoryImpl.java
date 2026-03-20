package com.vip.tycloud.repository.schedule.impl;

import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.schedule.TyResResourceBooking;
import com.vip.tycloud.mapper.schedule.TyResResourceBookingMapper;
import com.vip.tycloud.repository.schedule.TyResResourceBookingRepository;
import org.springframework.stereotype.Repository;

/**
 * 排课与资源 功能模块 - 资源预约 - 仓储实现类。
 */
@Repository
public class TyResResourceBookingRepositoryImpl extends BaseRepositoryImpl<TyResResourceBookingMapper, TyResResourceBooking> implements TyResResourceBookingRepository {

    public TyResResourceBookingRepositoryImpl(TyResResourceBookingMapper baseMapper) {
        super(baseMapper);
    }
}


