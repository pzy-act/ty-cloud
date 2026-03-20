package com.vip.tycloud.repository.student.impl;

import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.student.TyCrmLead;
import com.vip.tycloud.mapper.student.TyCrmLeadMapper;
import com.vip.tycloud.repository.student.TyCrmLeadRepository;
import org.springframework.stereotype.Repository;

/**
 * 学员管理 功能模块 - 线索管理 - 仓储实现类。
 */
@Repository
public class TyCrmLeadRepositoryImpl extends BaseRepositoryImpl<TyCrmLeadMapper, TyCrmLead> implements TyCrmLeadRepository {

    public TyCrmLeadRepositoryImpl(TyCrmLeadMapper baseMapper) {
        super(baseMapper);
    }
}


