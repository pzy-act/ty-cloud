package com.vip.tycloud.service.attendance.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.attendance.TyEduLeaveRequest;
import com.vip.tycloud.repository.attendance.TyEduLeaveRequestRepository;
import com.vip.tycloud.service.attendance.TyEduLeaveRequestService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 考勤与消课 功能模块 - 请假管理 - 服务实现类。
 */
@Service
@RequiredArgsConstructor
public class TyEduLeaveRequestServiceImpl implements TyEduLeaveRequestService {

    private final TyEduLeaveRequestRepository tyEduLeaveRequestRepository;

    @Override
    public TyEduLeaveRequest getById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return tyEduLeaveRequestRepository.getById(id);
    }

    @Override
    public PageResultDTO<TyEduLeaveRequest> page(Integer pageNumber, Integer pageSize) {
        long current = Objects.isNull(pageNumber) || pageNumber < 1 ? 1L : pageNumber;
        long size = Objects.isNull(pageSize) || pageSize < 1 ? 10L : pageSize;
        Page<TyEduLeaveRequest> page = new Page<>(current, size);
        IPage<TyEduLeaveRequest> pageResult = tyEduLeaveRequestRepository.page(
            page,
            Wrappers.<TyEduLeaveRequest>lambdaQuery()
                .eq(TyEduLeaveRequest::getIsDeleted, 0)
                .orderByDesc(TyEduLeaveRequest::getId)
        );
        return PageResultDTO.of(pageResult.getTotal(), pageResult.getRecords());
    }

    @Override
    public boolean save(TyEduLeaveRequest entity) {
        if (Objects.isNull(entity)) {
            return false;
        }
        entity.setId(null);
        if (Objects.isNull(entity.getIsDeleted())) {
            entity.setIsDeleted(0);
        }
        return tyEduLeaveRequestRepository.save(entity) > 0;
    }

    @Override
    public boolean updateById(TyEduLeaveRequest entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId())) {
            return false;
        }
        return tyEduLeaveRequestRepository.updateById(entity) > 0;
    }

    @Override
    public boolean deleteById(Long id, Long operatorId) {
        if (Objects.isNull(id)) {
            return false;
        }
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return tyEduLeaveRequestRepository.logicDeleteById(id, actualOperatorId) > 0;
    }
}


