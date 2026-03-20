package com.vip.tycloud.repository.report.impl;

import com.vip.tycloud.common.repository.BaseRepositoryImpl;
import com.vip.tycloud.entity.report.TyRptDailyKpi;
import com.vip.tycloud.mapper.report.TyRptDailyKpiMapper;
import com.vip.tycloud.repository.report.TyRptDailyKpiRepository;
import org.springframework.stereotype.Repository;

/**
 * 数据报表 功能模块 - 每日经营看板 - 仓储实现类。
 */
@Repository
public class TyRptDailyKpiRepositoryImpl extends BaseRepositoryImpl<TyRptDailyKpiMapper, TyRptDailyKpi> implements TyRptDailyKpiRepository {

    public TyRptDailyKpiRepositoryImpl(TyRptDailyKpiMapper baseMapper) {
        super(baseMapper);
    }
}


