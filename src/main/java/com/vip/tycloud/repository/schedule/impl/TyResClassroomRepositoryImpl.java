package com.vip.tycloud.repository.schedule.impl;

import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.schedule.TyResClassroom;
import com.vip.tycloud.mapper.schedule.TyResClassroomMapper;
import com.vip.tycloud.repository.schedule.TyResClassroomRepository;
import org.springframework.stereotype.Repository;

/**
 * 排课与资源 功能模块 - 教室资源 - 仓储实现类。
 */
@Repository
public class TyResClassroomRepositoryImpl extends BaseRepositoryImpl<TyResClassroomMapper, TyResClassroom> implements TyResClassroomRepository {

    public TyResClassroomRepositoryImpl(TyResClassroomMapper baseMapper) {
        super(baseMapper);
    }
}


