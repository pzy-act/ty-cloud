package com.vip.tycloud.service.schedule.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.schedule.TyResEquipment;
import com.vip.tycloud.repository.schedule.TyResEquipmentRepository;
import com.vip.tycloud.service.schedule.TyResEquipmentService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 排课与资源 功能模块 - 设备资源 - 服务实现类。
 */
@Service
@RequiredArgsConstructor
public class TyResEquipmentServiceImpl implements TyResEquipmentService {

    private final TyResEquipmentRepository tyResEquipmentRepository;

    @Override
    public TyResEquipment getById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return tyResEquipmentRepository.getById(id);
    }

    @Override
    public PageResultDTO<TyResEquipment> page(Integer pageNumber, Integer pageSize) {
        long current = Objects.isNull(pageNumber) || pageNumber < 1 ? 1L : pageNumber;
        long size = Objects.isNull(pageSize) || pageSize < 1 ? 10L : pageSize;
        Page<TyResEquipment> page = new Page<>(current, size);
        IPage<TyResEquipment> pageResult = tyResEquipmentRepository.page(
            page,
            Wrappers.<TyResEquipment>lambdaQuery()
                .eq(TyResEquipment::getIsDeleted, 0)
                .orderByDesc(TyResEquipment::getId)
        );
        return PageResultDTO.of(pageResult.getTotal(), pageResult.getRecords());
    }

    @Override
    public boolean save(TyResEquipment entity) {
        if (Objects.isNull(entity)) {
            return false;
        }
        entity.setId(null);
        if (Objects.isNull(entity.getIsDeleted())) {
            entity.setIsDeleted(0);
        }
        return tyResEquipmentRepository.save(entity) > 0;
    }

    @Override
    public boolean updateById(TyResEquipment entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId())) {
            return false;
        }
        return tyResEquipmentRepository.updateById(entity) > 0;
    }

    @Override
    public boolean deleteById(Long id, Long operatorId) {
        if (Objects.isNull(id)) {
            return false;
        }
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return tyResEquipmentRepository.logicDeleteById(id, actualOperatorId) > 0;
    }
}


