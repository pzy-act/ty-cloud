package com.vip.tycloud.service.finance.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.common.enums.TyPaymentStatusEnum;
import com.vip.tycloud.entity.finance.TyFinPayment;
import com.vip.tycloud.repository.finance.TyFinPaymentRepository;
import com.vip.tycloud.util.StatusTransitionUtils;
import com.vip.tycloud.service.finance.TyFinPaymentService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 收费与订单 功能模块 - 收款记录 - 服务实现类。
 */
@Service
@RequiredArgsConstructor
public class TyFinPaymentServiceImpl implements TyFinPaymentService {

    private final TyFinPaymentRepository tyFinPaymentRepository;

    @Override
    public TyFinPayment getById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return tyFinPaymentRepository.getById(id);
    }

    @Override
    public PageResultDTO<TyFinPayment> page(Integer pageNumber, Integer pageSize, String keyword, Integer status) {
        long current = Objects.isNull(pageNumber) || pageNumber < 1 ? 1L : pageNumber;
        long size = Objects.isNull(pageSize) || pageSize < 1 ? 10L : pageSize;
        Page<TyFinPayment> page = new Page<>(current, size);
        LambdaQueryWrapper<TyFinPayment> queryWrapper = Wrappers.<TyFinPayment>lambdaQuery()
            .eq(TyFinPayment::getIsDeleted, 0)
            .eq(Objects.nonNull(status), TyFinPayment::getStatus, status);
        if (StringUtils.hasText(keyword)) {
            String actualKeyword = keyword.trim();
            queryWrapper.and(wrapper -> wrapper
                .like(TyFinPayment::getPaymentNo, actualKeyword)
                .or()
                .like(TyFinPayment::getPayMethod, actualKeyword)
                .or()
                .like(TyFinPayment::getTransactionNo, actualKeyword)
            );
        }
        IPage<TyFinPayment> pageResult = tyFinPaymentRepository.page(
            page,
            queryWrapper.orderByDesc(TyFinPayment::getId)
        );
        return PageResultDTO.of(pageResult.getTotal(), pageResult.getRecords());
    }

    @Override
    public boolean save(TyFinPayment entity) {
        if (Objects.isNull(entity)) {
            return false;
        }
        entity.setId(null);
        if (Objects.isNull(entity.getIsDeleted())) {
            entity.setIsDeleted(0);
        }
        return tyFinPaymentRepository.save(entity) > 0;
    }

    @Override
    public boolean updateById(TyFinPayment entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId())) {
            return false;
        }
        return tyFinPaymentRepository.updateById(entity) > 0;
    }

    @Override
    public boolean deleteById(Long id, Long operatorId) {
        if (Objects.isNull(id)) {
            return false;
        }
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return tyFinPaymentRepository.logicDeleteById(id, actualOperatorId) > 0;
    }
}


