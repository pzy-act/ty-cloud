package com.vip.tycloud.repository.schedule.impl;

import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.schedule.TyResEquipment;
import com.vip.tycloud.mapper.schedule.TyResEquipmentMapper;
import com.vip.tycloud.repository.schedule.TyResEquipmentRepository;
import org.springframework.stereotype.Repository;

/**
 * 排课与资源 功能模块 - 设备资源 - 仓储实现类。
 */
@Repository
public class TyResEquipmentRepositoryImpl extends BaseRepositoryImpl<TyResEquipmentMapper, TyResEquipment> implements TyResEquipmentRepository {

    public TyResEquipmentRepositoryImpl(TyResEquipmentMapper baseMapper) {
        super(baseMapper);
    }
}


