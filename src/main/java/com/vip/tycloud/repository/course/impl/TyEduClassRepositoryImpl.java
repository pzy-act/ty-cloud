package com.vip.tycloud.repository.course.impl;

import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.course.TyEduClass;
import com.vip.tycloud.mapper.course.TyEduClassMapper;
import com.vip.tycloud.repository.course.TyEduClassRepository;
import org.springframework.stereotype.Repository;

/**
 * 课程与班级 功能模块 - 班级管理 - 仓储实现类。
 */
@Repository
public class TyEduClassRepositoryImpl extends BaseRepositoryImpl<TyEduClassMapper, TyEduClass> implements TyEduClassRepository {

    public TyEduClassRepositoryImpl(TyEduClassMapper baseMapper) {
        super(baseMapper);
    }
}


