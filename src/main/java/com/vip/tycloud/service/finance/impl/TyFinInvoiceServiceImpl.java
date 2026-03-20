package com.vip.tycloud.service.finance.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.finance.TyFinInvoice;
import com.vip.tycloud.repository.finance.TyFinInvoiceRepository;
import com.vip.tycloud.service.finance.TyFinInvoiceService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 收费与订单 功能模块 - 发票管理 - 服务实现类。
 */
@Service
@RequiredArgsConstructor
public class TyFinInvoiceServiceImpl implements TyFinInvoiceService {

    private final TyFinInvoiceRepository tyFinInvoiceRepository;

    @Override
    public TyFinInvoice getById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return tyFinInvoiceRepository.getById(id);
    }

    @Override
    public PageResultDTO<TyFinInvoice> page(Integer pageNumber, Integer pageSize) {
        long current = Objects.isNull(pageNumber) || pageNumber < 1 ? 1L : pageNumber;
        long size = Objects.isNull(pageSize) || pageSize < 1 ? 10L : pageSize;
        Page<TyFinInvoice> page = new Page<>(current, size);
        IPage<TyFinInvoice> pageResult = tyFinInvoiceRepository.page(
            page,
            Wrappers.<TyFinInvoice>lambdaQuery()
                .eq(TyFinInvoice::getIsDeleted, 0)
                .orderByDesc(TyFinInvoice::getId)
        );
        return PageResultDTO.of(pageResult.getTotal(), pageResult.getRecords());
    }

    @Override
    public boolean save(TyFinInvoice entity) {
        if (Objects.isNull(entity)) {
            return false;
        }
        entity.setId(null);
        if (Objects.isNull(entity.getIsDeleted())) {
            entity.setIsDeleted(0);
        }
        return tyFinInvoiceRepository.save(entity) > 0;
    }

    @Override
    public boolean updateById(TyFinInvoice entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId())) {
            return false;
        }
        return tyFinInvoiceRepository.updateById(entity) > 0;
    }

    @Override
    public boolean deleteById(Long id, Long operatorId) {
        if (Objects.isNull(id)) {
            return false;
        }
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return tyFinInvoiceRepository.logicDeleteById(id, actualOperatorId) > 0;
    }
}


