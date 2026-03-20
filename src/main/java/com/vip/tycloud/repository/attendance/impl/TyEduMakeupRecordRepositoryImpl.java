package com.vip.tycloud.repository.attendance.impl;

import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.attendance.TyEduMakeupRecord;
import com.vip.tycloud.mapper.attendance.TyEduMakeupRecordMapper;
import com.vip.tycloud.repository.attendance.TyEduMakeupRecordRepository;
import org.springframework.stereotype.Repository;

/**
 * 考勤与消课 功能模块 - 补课管理 - 仓储实现类。
 */
@Repository
public class TyEduMakeupRecordRepositoryImpl extends BaseRepositoryImpl<TyEduMakeupRecordMapper, TyEduMakeupRecord> implements TyEduMakeupRecordRepository {

    public TyEduMakeupRecordRepositoryImpl(TyEduMakeupRecordMapper baseMapper) {
        super(baseMapper);
    }
}


