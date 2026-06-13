package com.vip.tycloud.service.message.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.common.enums.TyProgressStatusEnum;
import com.vip.tycloud.util.PageQueryUtils;
import com.vip.tycloud.util.StatusTransitionUtils;
import com.vip.tycloud.entity.message.TyCrmFeedback;
import com.vip.tycloud.repository.message.TyCrmFeedbackRepository;
import com.vip.tycloud.service.message.TyCrmFeedbackService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 通知与互动 功能模块 - 家长反馈 - 服务实现类。
 */
@Service
@RequiredArgsConstructor
public class TyCrmFeedbackServiceImpl implements TyCrmFeedbackService {

    private final TyCrmFeedbackRepository tyCrmFeedbackRepository;

    @Override
    public TyCrmFeedback getById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return tyCrmFeedbackRepository.getById(id);
    }

    @Override
    public PageResultDTO<TyCrmFeedback> page(Integer pageNumber, Integer pageSize, String keyword, Integer status) {
        long current = Objects.isNull(pageNumber) || pageNumber < 1 ? 1L : pageNumber;
        long size = Objects.isNull(pageSize) || pageSize < 1 ? 10L : pageSize;
        Page<TyCrmFeedback> page = new Page<>(current, size);
        IPage<TyCrmFeedback> pageResult = tyCrmFeedbackRepository.page(
            page,
            PageQueryUtils.baseQuery(keyword, status, "handle_status", "feedback_type", "content")
        );
        return PageResultDTO.of(pageResult.getTotal(), pageResult.getRecords());
    }
    @Override
    public boolean save(TyCrmFeedback entity) {
        if (Objects.isNull(entity)) {
            return false;
        }
        entity.setId(null);
        if (Objects.isNull(entity.getIsDeleted())) {
            entity.setIsDeleted(0);
        }
        return tyCrmFeedbackRepository.save(entity) > 0;
    }

    @Override
    public boolean updateById(TyCrmFeedback entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId())) {
            return false;
        }
        return tyCrmFeedbackRepository.updateById(entity) > 0;
    }

    @Override
    public boolean deleteById(Long id, Long operatorId) {
        if (Objects.isNull(id)) {
            return false;
        }
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return tyCrmFeedbackRepository.logicDeleteById(id, actualOperatorId) > 0;
    }
}


