package com.vip.tycloud.service.report.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.report.TyRptDailyKpi;
import com.vip.tycloud.repository.report.TyRptDailyKpiRepository;
import com.vip.tycloud.service.report.TyRptDailyKpiService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 数据报表 功能模块 - 每日经营看板 - 服务实现类。
 */
@Service
@RequiredArgsConstructor
public class TyRptDailyKpiServiceImpl implements TyRptDailyKpiService {

    private final TyRptDailyKpiRepository tyRptDailyKpiRepository;

    @Override
    public TyRptDailyKpi getById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return tyRptDailyKpiRepository.getById(id);
    }

    @Override
    public PageResultDTO<TyRptDailyKpi> page(Integer pageNumber, Integer pageSize) {
        long current = Objects.isNull(pageNumber) || pageNumber < 1 ? 1L : pageNumber;
        long size = Objects.isNull(pageSize) || pageSize < 1 ? 10L : pageSize;
        Page<TyRptDailyKpi> page = new Page<>(current, size);
        IPage<TyRptDailyKpi> pageResult = tyRptDailyKpiRepository.page(
            page,
            Wrappers.<TyRptDailyKpi>lambdaQuery()
                .eq(TyRptDailyKpi::getIsDeleted, 0)
                .orderByDesc(TyRptDailyKpi::getId)
        );
        return PageResultDTO.of(pageResult.getTotal(), pageResult.getRecords());
    }

    @Override
    public boolean save(TyRptDailyKpi entity) {
        if (Objects.isNull(entity)) {
            return false;
        }
        entity.setId(null);
        if (Objects.isNull(entity.getIsDeleted())) {
            entity.setIsDeleted(0);
        }
        return tyRptDailyKpiRepository.save(entity) > 0;
    }

    @Override
    public boolean updateById(TyRptDailyKpi entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId())) {
            return false;
        }
        return tyRptDailyKpiRepository.updateById(entity) > 0;
    }

    @Override
    public boolean deleteById(Long id, Long operatorId) {
        if (Objects.isNull(id)) {
            return false;
        }
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return tyRptDailyKpiRepository.logicDeleteById(id, actualOperatorId) > 0;
    }
}


