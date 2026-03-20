package com.vip.tycloud.repository.teacher.impl;

import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.teacher.TyHrTeacherProfile;
import com.vip.tycloud.mapper.teacher.TyHrTeacherProfileMapper;
import com.vip.tycloud.repository.teacher.TyHrTeacherProfileRepository;
import org.springframework.stereotype.Repository;

/**
 * 教师与绩效 功能模块 - 教师档案 - 仓储实现类。
 */
@Repository
public class TyHrTeacherProfileRepositoryImpl extends BaseRepositoryImpl<TyHrTeacherProfileMapper, TyHrTeacherProfile> implements TyHrTeacherProfileRepository {

    public TyHrTeacherProfileRepositoryImpl(TyHrTeacherProfileMapper baseMapper) {
        super(baseMapper);
    }
}


