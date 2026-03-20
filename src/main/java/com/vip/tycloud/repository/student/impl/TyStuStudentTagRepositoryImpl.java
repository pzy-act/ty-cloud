package com.vip.tycloud.repository.student.impl;

import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.student.TyStuStudentTag;
import com.vip.tycloud.mapper.student.TyStuStudentTagMapper;
import com.vip.tycloud.repository.student.TyStuStudentTagRepository;
import org.springframework.stereotype.Repository;

/**
 * 学员管理 功能模块 - 学员标签关联 - 仓储实现类。
 */
@Repository
public class TyStuStudentTagRepositoryImpl extends BaseRepositoryImpl<TyStuStudentTagMapper, TyStuStudentTag> implements TyStuStudentTagRepository {

    public TyStuStudentTagRepositoryImpl(TyStuStudentTagMapper baseMapper) {
        super(baseMapper);
    }
}


