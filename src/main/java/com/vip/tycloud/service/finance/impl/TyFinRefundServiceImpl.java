package com.vip.tycloud.service.finance.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.common.enums.TyRefundStatusEnum;
import com.vip.tycloud.entity.finance.TyFinRefund;
import com.vip.tycloud.repository.finance.TyFinRefundRepository;
import com.vip.tycloud.util.StatusTransitionUtils;
import com.vip.tycloud.service.finance.TyFinRefundService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 收费与订单 功能模块 - 退款记录 - 服务实现类。
 */
@Service
@RequiredArgsConstructor
public class TyFinRefundServiceImpl implements TyFinRefundService {

    private final TyFinRefundRepository tyFinRefundRepository;

    @Override
    public TyFinRefund getById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return tyFinRefundRepository.getById(id);
    }

    @Override
    public PageResultDTO<TyFinRefund> page(Integer pageNumber, Integer pageSize, String keyword, Integer status) {
        long current = Objects.isNull(pageNumber) || pageNumber < 1 ? 1L : pageNumber;
        long size = Objects.isNull(pageSize) || pageSize < 1 ? 10L : pageSize;
        Page<TyFinRefund> page = new Page<>(current, size);
        LambdaQueryWrapper<TyFinRefund> queryWrapper = Wrappers.<TyFinRefund>lambdaQuery()
            .eq(TyFinRefund::getIsDeleted, 0)
            .eq(Objects.nonNull(status), TyFinRefund::getStatus, status);
        if (StringUtils.hasText(keyword)) {
            String actualKeyword = keyword.trim();
            queryWrapper.and(wrapper -> wrapper
                .like(TyFinRefund::getRefundNo, actualKeyword)
                .or()
                .like(TyFinRefund::getReason, actualKeyword)
            );
        }
        IPage<TyFinRefund> pageResult = tyFinRefundRepository.page(
            page,
            queryWrapper.orderByDesc(TyFinRefund::getId)
        );
        return PageResultDTO.of(pageResult.getTotal(), pageResult.getRecords());
    }

    @Override
    public boolean save(TyFinRefund entity) {
        if (Objects.isNull(entity)) {
            return false;
        }
        entity.setId(null);
        if (Objects.isNull(entity.getIsDeleted())) {
            entity.setIsDeleted(0);
        }
        return tyFinRefundRepository.save(entity) > 0;
    }

    @Override
    public boolean updateById(TyFinRefund entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId())) {
            return false;
        }
        return tyFinRefundRepository.updateById(entity) > 0;
    }

    @Override
    public boolean deleteById(Long id, Long operatorId) {
        if (Objects.isNull(id)) {
            return false;
        }
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return tyFinRefundRepository.logicDeleteById(id, actualOperatorId) > 0;
    }
}


