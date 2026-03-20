package com.vip.tycloud.repository.attendance.impl;

import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.attendance.TyEduLessonDeductLog;
import com.vip.tycloud.mapper.attendance.TyEduLessonDeductLogMapper;
import com.vip.tycloud.repository.attendance.TyEduLessonDeductLogRepository;
import org.springframework.stereotype.Repository;

/**
 * 考勤与消课 功能模块 - 消课记录 - 仓储实现类。
 */
@Repository
public class TyEduLessonDeductLogRepositoryImpl extends BaseRepositoryImpl<TyEduLessonDeductLogMapper, TyEduLessonDeductLog> implements TyEduLessonDeductLogRepository {

    public TyEduLessonDeductLogRepositoryImpl(TyEduLessonDeductLogMapper baseMapper) {
        super(baseMapper);
    }
}


