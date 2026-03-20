package com.vip.tycloud.repository.student.impl;

import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.student.TyStuStudent;
import com.vip.tycloud.mapper.student.TyStuStudentMapper;
import com.vip.tycloud.repository.student.TyStuStudentRepository;
import org.springframework.stereotype.Repository;

/**
 * 学员管理 功能模块 - 学员档案 - 仓储实现类。
 */
@Repository
public class TyStuStudentRepositoryImpl extends BaseRepositoryImpl<TyStuStudentMapper, TyStuStudent> implements TyStuStudentRepository {

    public TyStuStudentRepositoryImpl(TyStuStudentMapper baseMapper) {
        super(baseMapper);
    }
}


