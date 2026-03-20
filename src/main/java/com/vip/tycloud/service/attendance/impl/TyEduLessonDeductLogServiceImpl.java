package com.vip.tycloud.service.attendance.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.attendance.TyEduLessonDeductLog;
import com.vip.tycloud.repository.attendance.TyEduLessonDeductLogRepository;
import com.vip.tycloud.service.attendance.TyEduLessonDeductLogService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 考勤与消课 功能模块 - 消课记录 - 服务实现类。
 */
@Service
@RequiredArgsConstructor
public class TyEduLessonDeductLogServiceImpl implements TyEduLessonDeductLogService {

    private final TyEduLessonDeductLogRepository tyEduLessonDeductLogRepository;

    @Override
    public TyEduLessonDeductLog getById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return tyEduLessonDeductLogRepository.getById(id);
    }

    @Override
    public PageResultDTO<TyEduLessonDeductLog> page(Integer pageNumber, Integer pageSize) {
        long current = Objects.isNull(pageNumber) || pageNumber < 1 ? 1L : pageNumber;
        long size = Objects.isNull(pageSize) || pageSize < 1 ? 10L : pageSize;
        Page<TyEduLessonDeductLog> page = new Page<>(current, size);
        IPage<TyEduLessonDeductLog> pageResult = tyEduLessonDeductLogRepository.page(
            page,
            Wrappers.<TyEduLessonDeductLog>lambdaQuery()
                .eq(TyEduLessonDeductLog::getIsDeleted, 0)
                .orderByDesc(TyEduLessonDeductLog::getId)
        );
        return PageResultDTO.of(pageResult.getTotal(), pageResult.getRecords());
    }

    @Override
    public boolean save(TyEduLessonDeductLog entity) {
        if (Objects.isNull(entity)) {
            return false;
        }
        entity.setId(null);
        if (Objects.isNull(entity.getIsDeleted())) {
            entity.setIsDeleted(0);
        }
        return tyEduLessonDeductLogRepository.save(entity) > 0;
    }

    @Override
    public boolean updateById(TyEduLessonDeductLog entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId())) {
            return false;
        }
        return tyEduLessonDeductLogRepository.updateById(entity) > 0;
    }

    @Override
    public boolean deleteById(Long id, Long operatorId) {
        if (Objects.isNull(id)) {
            return false;
        }
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return tyEduLessonDeductLogRepository.logicDeleteById(id, actualOperatorId) > 0;
    }
}


