package com.vip.tycloud.service.student.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.student.TyStuTag;
import com.vip.tycloud.repository.student.TyStuTagRepository;
import com.vip.tycloud.service.student.TyStuTagService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 学员管理 功能模块 - 学员标签 - 服务实现类。
 */
@Service
@RequiredArgsConstructor
public class TyStuTagServiceImpl implements TyStuTagService {

    private final TyStuTagRepository tyStuTagRepository;

    @Override
    public TyStuTag getById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return tyStuTagRepository.getById(id);
    }

    @Override
    public PageResultDTO<TyStuTag> page(Integer pageNumber, Integer pageSize) {
        long current = Objects.isNull(pageNumber) || pageNumber < 1 ? 1L : pageNumber;
        long size = Objects.isNull(pageSize) || pageSize < 1 ? 10L : pageSize;
        Page<TyStuTag> page = new Page<>(current, size);
        IPage<TyStuTag> pageResult = tyStuTagRepository.page(
            page,
            Wrappers.<TyStuTag>lambdaQuery()
                .eq(TyStuTag::getIsDeleted, 0)
                .orderByDesc(TyStuTag::getId)
        );
        return PageResultDTO.of(pageResult.getTotal(), pageResult.getRecords());
    }

    @Override
    public boolean save(TyStuTag entity) {
        if (Objects.isNull(entity)) {
            return false;
        }
        entity.setId(null);
        if (Objects.isNull(entity.getIsDeleted())) {
            entity.setIsDeleted(0);
        }
        return tyStuTagRepository.save(entity) > 0;
    }

    @Override
    public boolean updateById(TyStuTag entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId())) {
            return false;
        }
        return tyStuTagRepository.updateById(entity) > 0;
    }

    @Override
    public boolean deleteById(Long id, Long operatorId) {
        if (Objects.isNull(id)) {
            return false;
        }
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return tyStuTagRepository.logicDeleteById(id, actualOperatorId) > 0;
    }
}


