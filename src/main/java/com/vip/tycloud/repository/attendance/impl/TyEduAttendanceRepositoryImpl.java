package com.vip.tycloud.repository.attendance.impl;

import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.attendance.TyEduAttendance;
import com.vip.tycloud.mapper.attendance.TyEduAttendanceMapper;
import com.vip.tycloud.repository.attendance.TyEduAttendanceRepository;
import org.springframework.stereotype.Repository;

/**
 * 考勤与消课 功能模块 - 签到考勤 - 仓储实现类。
 */
@Repository
public class TyEduAttendanceRepositoryImpl extends BaseRepositoryImpl<TyEduAttendanceMapper, TyEduAttendance> implements TyEduAttendanceRepository {

    public TyEduAttendanceRepositoryImpl(TyEduAttendanceMapper baseMapper) {
        super(baseMapper);
    }
}


