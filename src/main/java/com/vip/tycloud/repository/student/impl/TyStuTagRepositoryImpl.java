package com.vip.tycloud.repository.student.impl;

import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.student.TyStuTag;
import com.vip.tycloud.mapper.student.TyStuTagMapper;
import com.vip.tycloud.repository.student.TyStuTagRepository;
import org.springframework.stereotype.Repository;

/**
 * 学员管理 功能模块 - 学员标签 - 仓储实现类。
 */
@Repository
public class TyStuTagRepositoryImpl extends BaseRepositoryImpl<TyStuTagMapper, TyStuTag> implements TyStuTagRepository {

    public TyStuTagRepositoryImpl(TyStuTagMapper baseMapper) {
        super(baseMapper);
    }
}


