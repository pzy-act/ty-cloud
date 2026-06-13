package com.vip.tycloud.service.artwork.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.util.PageQueryUtils;
import com.vip.tycloud.entity.artwork.TyArtGlazeRecord;
import com.vip.tycloud.repository.artwork.TyArtGlazeRecordRepository;
import com.vip.tycloud.service.artwork.TyArtGlazeRecordService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 陶艺作品管理 功能模块 - 上釉记录 - 服务实现类。
 */
@Service
@RequiredArgsConstructor
public class TyArtGlazeRecordServiceImpl implements TyArtGlazeRecordService {

    private final TyArtGlazeRecordRepository tyArtGlazeRecordRepository;

    @Override
    public TyArtGlazeRecord getById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return tyArtGlazeRecordRepository.getById(id);
    }

    @Override
    public PageResultDTO<TyArtGlazeRecord> page(Integer pageNumber, Integer pageSize, String keyword, Integer status) {
        long current = Objects.isNull(pageNumber) || pageNumber < 1 ? 1L : pageNumber;
        long size = Objects.isNull(pageSize) || pageSize < 1 ? 10L : pageSize;
        Page<TyArtGlazeRecord> page = new Page<>(current, size);
        IPage<TyArtGlazeRecord> pageResult = tyArtGlazeRecordRepository.page(
            page,
            PageQueryUtils.baseQuery(keyword, status, null, "glaze_color_code", "glaze_color_name", "remark")
        );
        return PageResultDTO.of(pageResult.getTotal(), pageResult.getRecords());
    }
    @Override
    public boolean save(TyArtGlazeRecord entity) {
        if (Objects.isNull(entity)) {
            return false;
        }
        entity.setId(null);
        if (Objects.isNull(entity.getIsDeleted())) {
            entity.setIsDeleted(0);
        }
        return tyArtGlazeRecordRepository.save(entity) > 0;
    }

    @Override
    public boolean updateById(TyArtGlazeRecord entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId())) {
            return false;
        }
        return tyArtGlazeRecordRepository.updateById(entity) > 0;
    }

    @Override
    public boolean deleteById(Long id, Long operatorId) {
        if (Objects.isNull(id)) {
            return false;
        }
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return tyArtGlazeRecordRepository.logicDeleteById(id, actualOperatorId) > 0;
    }
}


