package com.vip.tycloud.repository.student.impl;

import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.student.TyStuGuardian;
import com.vip.tycloud.mapper.student.TyStuGuardianMapper;
import com.vip.tycloud.repository.student.TyStuGuardianRepository;
import org.springframework.stereotype.Repository;

/**
 * 学员管理 功能模块 - 监护人管理 - 仓储实现类。
 */
@Repository
public class TyStuGuardianRepositoryImpl extends BaseRepositoryImpl<TyStuGuardianMapper, TyStuGuardian> implements TyStuGuardianRepository {

    public TyStuGuardianRepositoryImpl(TyStuGuardianMapper baseMapper) {
        super(baseMapper);
    }
}


