package com.vip.tycloud.repository.course.impl;

import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.course.TyEduCourse;
import com.vip.tycloud.mapper.course.TyEduCourseMapper;
import com.vip.tycloud.repository.course.TyEduCourseRepository;
import org.springframework.stereotype.Repository;

/**
 * 课程与班级 功能模块 - 课程管理 - 仓储实现类。
 */
@Repository
public class TyEduCourseRepositoryImpl extends BaseRepositoryImpl<TyEduCourseMapper, TyEduCourse> implements TyEduCourseRepository {

    public TyEduCourseRepositoryImpl(TyEduCourseMapper baseMapper) {
        super(baseMapper);
    }
}


