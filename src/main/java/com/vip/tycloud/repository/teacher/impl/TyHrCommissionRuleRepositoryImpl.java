package com.vip.tycloud.repository.teacher.impl;

import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.teacher.TyHrCommissionRule;
import com.vip.tycloud.mapper.teacher.TyHrCommissionRuleMapper;
import com.vip.tycloud.repository.teacher.TyHrCommissionRuleRepository;
import org.springframework.stereotype.Repository;

/**
 * 教师与绩效 功能模块 - 提成规则 - 仓储实现类。
 */
@Repository
public class TyHrCommissionRuleRepositoryImpl extends BaseRepositoryImpl<TyHrCommissionRuleMapper, TyHrCommissionRule> implements TyHrCommissionRuleRepository {

    public TyHrCommissionRuleRepositoryImpl(TyHrCommissionRuleMapper baseMapper) {
        super(baseMapper);
    }
}


