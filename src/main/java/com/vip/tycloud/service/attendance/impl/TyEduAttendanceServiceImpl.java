package com.vip.tycloud.service.attendance.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.attendance.TyEduAttendance;
import com.vip.tycloud.repository.attendance.TyEduAttendanceRepository;
import com.vip.tycloud.service.attendance.TyEduAttendanceService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 考勤与消课 功能模块 - 签到考勤 - 服务实现类。
 */
@Service
@RequiredArgsConstructor
public class TyEduAttendanceServiceImpl implements TyEduAttendanceService {

    private final TyEduAttendanceRepository tyEduAttendanceRepository;

    @Override
    public TyEduAttendance getById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return tyEduAttendanceRepository.getById(id);
    }

    @Override
    public PageResultDTO<TyEduAttendance> page(Integer pageNumber, Integer pageSize) {
        long current = Objects.isNull(pageNumber) || pageNumber < 1 ? 1L : pageNumber;
        long size = Objects.isNull(pageSize) || pageSize < 1 ? 10L : pageSize;
        Page<TyEduAttendance> page = new Page<>(current, size);
        IPage<TyEduAttendance> pageResult = tyEduAttendanceRepository.page(
            page,
            Wrappers.<TyEduAttendance>lambdaQuery()
                .eq(TyEduAttendance::getIsDeleted, 0)
                .orderByDesc(TyEduAttendance::getId)
        );
        return PageResultDTO.of(pageResult.getTotal(), pageResult.getRecords());
    }

    @Override
    public boolean save(TyEduAttendance entity) {
        if (Objects.isNull(entity)) {
            return false;
        }
        entity.setId(null);
        if (Objects.isNull(entity.getIsDeleted())) {
            entity.setIsDeleted(0);
        }
        return tyEduAttendanceRepository.save(entity) > 0;
    }

    @Override
    public boolean updateById(TyEduAttendance entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId())) {
            return false;
        }
        return tyEduAttendanceRepository.updateById(entity) > 0;
    }

    @Override
    public boolean deleteById(Long id, Long operatorId) {
        if (Objects.isNull(id)) {
            return false;
        }
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return tyEduAttendanceRepository.logicDeleteById(id, actualOperatorId) > 0;
    }
}


