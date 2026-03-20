package com.vip.tycloud.service.student.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.student.TyCrmChannel;
import com.vip.tycloud.repository.student.TyCrmChannelRepository;
import com.vip.tycloud.service.student.TyCrmChannelService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 学员管理 功能模块 - 渠道管理 - 服务实现类。
 */
@Service
@RequiredArgsConstructor
public class TyCrmChannelServiceImpl implements TyCrmChannelService {

    private final TyCrmChannelRepository tyCrmChannelRepository;

    @Override
    public TyCrmChannel getById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return tyCrmChannelRepository.getById(id);
    }

    @Override
    public PageResultDTO<TyCrmChannel> page(Integer pageNumber, Integer pageSize) {
        long current = Objects.isNull(pageNumber) || pageNumber < 1 ? 1L : pageNumber;
        long size = Objects.isNull(pageSize) || pageSize < 1 ? 10L : pageSize;
        Page<TyCrmChannel> page = new Page<>(current, size);
        IPage<TyCrmChannel> pageResult = tyCrmChannelRepository.page(
            page,
            Wrappers.<TyCrmChannel>lambdaQuery()
                .eq(TyCrmChannel::getIsDeleted, 0)
                .orderByDesc(TyCrmChannel::getId)
        );
        return PageResultDTO.of(pageResult.getTotal(), pageResult.getRecords());
    }

    @Override
    public boolean save(TyCrmChannel entity) {
        if (Objects.isNull(entity)) {
            return false;
        }
        entity.setId(null);
        if (Objects.isNull(entity.getIsDeleted())) {
            entity.setIsDeleted(0);
        }
        return tyCrmChannelRepository.save(entity) > 0;
    }

    @Override
    public boolean updateById(TyCrmChannel entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId())) {
            return false;
        }
        return tyCrmChannelRepository.updateById(entity) > 0;
    }

    @Override
    public boolean deleteById(Long id, Long operatorId) {
        if (Objects.isNull(id)) {
            return false;
        }
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return tyCrmChannelRepository.logicDeleteById(id, actualOperatorId) > 0;
    }
}


