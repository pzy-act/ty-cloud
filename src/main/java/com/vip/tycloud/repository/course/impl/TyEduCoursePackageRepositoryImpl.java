package com.vip.tycloud.repository.course.impl;

import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.course.TyEduCoursePackage;
import com.vip.tycloud.mapper.course.TyEduCoursePackageMapper;
import com.vip.tycloud.repository.course.TyEduCoursePackageRepository;
import org.springframework.stereotype.Repository;

/**
 * 课程与班级 功能模块 - 课包管理 - 仓储实现类。
 */
@Repository
public class TyEduCoursePackageRepositoryImpl extends BaseRepositoryImpl<TyEduCoursePackageMapper, TyEduCoursePackage> implements TyEduCoursePackageRepository {

    public TyEduCoursePackageRepositoryImpl(TyEduCoursePackageMapper baseMapper) {
        super(baseMapper);
    }
}


