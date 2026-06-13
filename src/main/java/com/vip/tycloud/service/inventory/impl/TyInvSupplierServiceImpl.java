package com.vip.tycloud.service.inventory.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.common.enums.TyBaseStatusEnum;
import com.vip.tycloud.util.PageQueryUtils;
import com.vip.tycloud.util.StatusTransitionUtils;
import com.vip.tycloud.entity.inventory.TyInvSupplier;
import com.vip.tycloud.repository.inventory.TyInvSupplierRepository;
import com.vip.tycloud.service.inventory.TyInvSupplierService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 耗材与库存 功能模块 - 供应商管理 - 服务实现类。
 */
@Service
@RequiredArgsConstructor
public class TyInvSupplierServiceImpl implements TyInvSupplierService {

    private final TyInvSupplierRepository tyInvSupplierRepository;

    @Override
    public TyInvSupplier getById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return tyInvSupplierRepository.getById(id);
    }

    @Override
    public PageResultDTO<TyInvSupplier> page(Integer pageNumber, Integer pageSize, String keyword, Integer status) {
        long current = Objects.isNull(pageNumber) || pageNumber < 1 ? 1L : pageNumber;
        long size = Objects.isNull(pageSize) || pageSize < 1 ? 10L : pageSize;
        Page<TyInvSupplier> page = new Page<>(current, size);
        IPage<TyInvSupplier> pageResult = tyInvSupplierRepository.page(
            page,
            PageQueryUtils.baseQuery(keyword, status, "status", "supplier_code", "supplier_name", "contact_name", "contact_phone", "address")
        );
        return PageResultDTO.of(pageResult.getTotal(), pageResult.getRecords());
    }
    @Override
    public boolean save(TyInvSupplier entity) {
        if (Objects.isNull(entity)) {
            return false;
        }
        entity.setId(null);
        if (Objects.isNull(entity.getIsDeleted())) {
            entity.setIsDeleted(0);
        }
        return tyInvSupplierRepository.save(entity) > 0;
    }

    @Override
    public boolean updateById(TyInvSupplier entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId())) {
            return false;
        }
        return tyInvSupplierRepository.updateById(entity) > 0;
    }

    @Override
    public boolean deleteById(Long id, Long operatorId) {
        if (Objects.isNull(id)) {
            return false;
        }
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return tyInvSupplierRepository.logicDeleteById(id, actualOperatorId) > 0;
    }
}


