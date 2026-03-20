package com.vip.tycloud.repository.student.impl;

import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.student.TyStuStudentGuardian;
import com.vip.tycloud.mapper.student.TyStuStudentGuardianMapper;
import com.vip.tycloud.repository.student.TyStuStudentGuardianRepository;
import org.springframework.stereotype.Repository;

/**
 * 学员管理 功能模块 - 学员监护关系 - 仓储实现类。
 */
@Repository
public class TyStuStudentGuardianRepositoryImpl extends BaseRepositoryImpl<TyStuStudentGuardianMapper, TyStuStudentGuardian> implements TyStuStudentGuardianRepository {

    public TyStuStudentGuardianRepositoryImpl(TyStuStudentGuardianMapper baseMapper) {
        super(baseMapper);
    }
}


