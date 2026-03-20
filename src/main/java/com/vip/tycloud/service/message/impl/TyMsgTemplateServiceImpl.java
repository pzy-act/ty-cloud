package com.vip.tycloud.service.message.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.message.TyMsgTemplate;
import com.vip.tycloud.repository.message.TyMsgTemplateRepository;
import com.vip.tycloud.service.message.TyMsgTemplateService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 通知与互动 功能模块 - 消息模板 - 服务实现类。
 */
@Service
@RequiredArgsConstructor
public class TyMsgTemplateServiceImpl implements TyMsgTemplateService {

    private final TyMsgTemplateRepository tyMsgTemplateRepository;

    @Override
    public TyMsgTemplate getById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return tyMsgTemplateRepository.getById(id);
    }

    @Override
    public PageResultDTO<TyMsgTemplate> page(Integer pageNumber, Integer pageSize) {
        long current = Objects.isNull(pageNumber) || pageNumber < 1 ? 1L : pageNumber;
        long size = Objects.isNull(pageSize) || pageSize < 1 ? 10L : pageSize;
        Page<TyMsgTemplate> page = new Page<>(current, size);
        IPage<TyMsgTemplate> pageResult = tyMsgTemplateRepository.page(
            page,
            Wrappers.<TyMsgTemplate>lambdaQuery()
                .eq(TyMsgTemplate::getIsDeleted, 0)
                .orderByDesc(TyMsgTemplate::getId)
        );
        return PageResultDTO.of(pageResult.getTotal(), pageResult.getRecords());
    }

    @Override
    public boolean save(TyMsgTemplate entity) {
        if (Objects.isNull(entity)) {
            return false;
        }
        entity.setId(null);
        if (Objects.isNull(entity.getIsDeleted())) {
            entity.setIsDeleted(0);
        }
        return tyMsgTemplateRepository.save(entity) > 0;
    }

    @Override
    public boolean updateById(TyMsgTemplate entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId())) {
            return false;
        }
        return tyMsgTemplateRepository.updateById(entity) > 0;
    }

    @Override
    public boolean deleteById(Long id, Long operatorId) {
        if (Objects.isNull(id)) {
            return false;
        }
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return tyMsgTemplateRepository.logicDeleteById(id, actualOperatorId) > 0;
    }
}


