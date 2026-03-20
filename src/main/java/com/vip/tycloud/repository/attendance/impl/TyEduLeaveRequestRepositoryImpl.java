package com.vip.tycloud.repository.attendance.impl;

import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.attendance.TyEduLeaveRequest;
import com.vip.tycloud.mapper.attendance.TyEduLeaveRequestMapper;
import com.vip.tycloud.repository.attendance.TyEduLeaveRequestRepository;
import org.springframework.stereotype.Repository;

/**
 * 考勤与消课 功能模块 - 请假管理 - 仓储实现类。
 */
@Repository
public class TyEduLeaveRequestRepositoryImpl extends BaseRepositoryImpl<TyEduLeaveRequestMapper, TyEduLeaveRequest> implements TyEduLeaveRequestRepository {

    public TyEduLeaveRequestRepositoryImpl(TyEduLeaveRequestMapper baseMapper) {
        super(baseMapper);
    }
}


