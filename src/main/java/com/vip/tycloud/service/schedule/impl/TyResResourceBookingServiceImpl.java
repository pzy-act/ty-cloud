package com.vip.tycloud.service.schedule.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.schedule.TyResResourceBooking;
import com.vip.tycloud.repository.schedule.TyResResourceBookingRepository;
import com.vip.tycloud.service.schedule.TyResResourceBookingService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 排课与资源 功能模块 - 资源预约 - 服务实现类。
 */
@Service
@RequiredArgsConstructor
public class TyResResourceBookingServiceImpl implements TyResResourceBookingService {

    private final TyResResourceBookingRepository tyResResourceBookingRepository;

    @Override
    public TyResResourceBooking getById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return tyResResourceBookingRepository.getById(id);
    }

    @Override
    public PageResultDTO<TyResResourceBooking> page(Integer pageNumber, Integer pageSize) {
        long current = Objects.isNull(pageNumber) || pageNumber < 1 ? 1L : pageNumber;
        long size = Objects.isNull(pageSize) || pageSize < 1 ? 10L : pageSize;
        Page<TyResResourceBooking> page = new Page<>(current, size);
        IPage<TyResResourceBooking> pageResult = tyResResourceBookingRepository.page(
            page,
            Wrappers.<TyResResourceBooking>lambdaQuery()
                .eq(TyResResourceBooking::getIsDeleted, 0)
                .orderByDesc(TyResResourceBooking::getId)
        );
        return PageResultDTO.of(pageResult.getTotal(), pageResult.getRecords());
    }

    @Override
    public boolean save(TyResResourceBooking entity) {
        if (Objects.isNull(entity)) {
            return false;
        }
        entity.setId(null);
        if (Objects.isNull(entity.getIsDeleted())) {
            entity.setIsDeleted(0);
        }
        return tyResResourceBookingRepository.save(entity) > 0;
    }

    @Override
    public boolean updateById(TyResResourceBooking entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId())) {
            return false;
        }
        return tyResResourceBookingRepository.updateById(entity) > 0;
    }

    @Override
    public boolean deleteById(Long id, Long operatorId) {
        if (Objects.isNull(id)) {
            return false;
        }
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return tyResResourceBookingRepository.logicDeleteById(id, actualOperatorId) > 0;
    }
}


