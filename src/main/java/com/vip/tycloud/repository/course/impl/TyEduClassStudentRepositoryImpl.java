package com.vip.tycloud.repository.course.impl;

import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.course.TyEduClassStudent;
import com.vip.tycloud.mapper.course.TyEduClassStudentMapper;
import com.vip.tycloud.repository.course.TyEduClassStudentRepository;
import org.springframework.stereotype.Repository;

/**
 * 课程与班级 功能模块 - 班级学员关联 - 仓储实现类。
 */
@Repository
public class TyEduClassStudentRepositoryImpl extends BaseRepositoryImpl<TyEduClassStudentMapper, TyEduClassStudent> implements TyEduClassStudentRepository {

    public TyEduClassStudentRepositoryImpl(TyEduClassStudentMapper baseMapper) {
        super(baseMapper);
    }
}


