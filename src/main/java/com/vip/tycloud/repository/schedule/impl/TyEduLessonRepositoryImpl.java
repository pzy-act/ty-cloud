package com.vip.tycloud.repository.schedule.impl;

import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.schedule.TyEduLesson;
import com.vip.tycloud.mapper.schedule.TyEduLessonMapper;
import com.vip.tycloud.repository.schedule.TyEduLessonRepository;
import org.springframework.stereotype.Repository;

/**
 * 排课与资源 功能模块 - 课表管理 - 仓储实现类。
 */
@Repository
public class TyEduLessonRepositoryImpl extends BaseRepositoryImpl<TyEduLessonMapper, TyEduLesson> implements TyEduLessonRepository {

    public TyEduLessonRepositoryImpl(TyEduLessonMapper baseMapper) {
        super(baseMapper);
    }
}


