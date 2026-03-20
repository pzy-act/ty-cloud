package com.vip.tycloud.service.system.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.system.TySysUser;
import com.vip.tycloud.repository.system.TySysUserRepository;
import com.vip.tycloud.service.system.TySysUserService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 组织与权限 功能模块 - 用户管理 - 服务实现类。
 */
@Service
@RequiredArgsConstructor
public class TySysUserServiceImpl implements TySysUserService {

    private final TySysUserRepository tySysUserRepository;

    @Override
    public TySysUser getById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return tySysUserRepository.getById(id);
    }

    @Override
    public PageResultDTO<TySysUser> page(Integer pageNumber, Integer pageSize) {
        long current = Objects.isNull(pageNumber) || pageNumber < 1 ? 1L : pageNumber;
        long size = Objects.isNull(pageSize) || pageSize < 1 ? 10L : pageSize;
        Page<TySysUser> page = new Page<>(current, size);
        IPage<TySysUser> pageResult = tySysUserRepository.page(
            page,
            Wrappers.<TySysUser>lambdaQuery()
                .eq(TySysUser::getIsDeleted, 0)
                .orderByDesc(TySysUser::getId)
        );
        return PageResultDTO.of(pageResult.getTotal(), pageResult.getRecords());
    }

    @Override
    public boolean save(TySysUser entity) {
        if (Objects.isNull(entity)) {
            return false;
        }
        entity.setId(null);
        if (Objects.isNull(entity.getIsDeleted())) {
            entity.setIsDeleted(0);
        }
        return tySysUserRepository.save(entity) > 0;
    }

    @Override
    public boolean updateById(TySysUser entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId())) {
            return false;
        }
        return tySysUserRepository.updateById(entity) > 0;
    }

    @Override
    public boolean deleteById(Long id, Long operatorId) {
        if (Objects.isNull(id)) {
            return false;
        }
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return tySysUserRepository.logicDeleteById(id, actualOperatorId) > 0;
    }
}


