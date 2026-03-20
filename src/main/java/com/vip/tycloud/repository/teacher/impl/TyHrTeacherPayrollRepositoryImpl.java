package com.vip.tycloud.repository.teacher.impl;

import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.teacher.TyHrTeacherPayroll;
import com.vip.tycloud.mapper.teacher.TyHrTeacherPayrollMapper;
import com.vip.tycloud.repository.teacher.TyHrTeacherPayrollRepository;
import org.springframework.stereotype.Repository;

/**
 * 教师与绩效 功能模块 - 工资核算 - 仓储实现类。
 */
@Repository
public class TyHrTeacherPayrollRepositoryImpl extends BaseRepositoryImpl<TyHrTeacherPayrollMapper, TyHrTeacherPayroll> implements TyHrTeacherPayrollRepository {

    public TyHrTeacherPayrollRepositoryImpl(TyHrTeacherPayrollMapper baseMapper) {
        super(baseMapper);
    }
}


