package com.vip.tycloud.service.teacher.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.dto.teacher.TyHrTeacherProfileRespDTO;
import com.vip.tycloud.entity.system.TySysUser;
import com.vip.tycloud.entity.teacher.TyHrTeacherProfile;
import com.vip.tycloud.repository.system.TySysUserRepository;
import com.vip.tycloud.repository.teacher.TyHrTeacherProfileRepository;
import com.vip.tycloud.service.teacher.TyHrTeacherProfileService;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 教师与绩效 功能模块 - 教师档案 - 服务实现类。
 */
@Service
@RequiredArgsConstructor
public class TyHrTeacherProfileServiceImpl implements TyHrTeacherProfileService {

    private final TyHrTeacherProfileRepository tyHrTeacherProfileRepository;
    private final TySysUserRepository tySysUserRepository;

    @Override
    public TyHrTeacherProfile getById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return tyHrTeacherProfileRepository.getById(id);
    }

    @Override
    public TyHrTeacherProfileRespDTO getRespById(Long id) {
        TyHrTeacherProfile entity = getById(id);
        if (Objects.isNull(entity)) {
            return null;
        }
        return toRespDTO(entity, buildUserMap(Collections.singletonList(entity)));
    }

    @Override
    public PageResultDTO<TyHrTeacherProfile> page(Integer pageNumber, Integer pageSize) {
        long current = Objects.isNull(pageNumber) || pageNumber < 1 ? 1L : pageNumber;
        long size = Objects.isNull(pageSize) || pageSize < 1 ? 10L : pageSize;
        Page<TyHrTeacherProfile> page = new Page<>(current, size);
        IPage<TyHrTeacherProfile> pageResult = tyHrTeacherProfileRepository.page(
            page,
            Wrappers.<TyHrTeacherProfile>lambdaQuery()
                .eq(TyHrTeacherProfile::getIsDeleted, 0)
                .orderByDesc(TyHrTeacherProfile::getId)
        );
        return PageResultDTO.of(pageResult.getTotal(), pageResult.getRecords());
    }

    @Override
    public PageResultDTO<TyHrTeacherProfileRespDTO> pageResp(Integer pageNumber, Integer pageSize) {
        PageResultDTO<TyHrTeacherProfile> pageResult = page(pageNumber, pageSize);
        Map<Long, TySysUser> userMap = buildUserMap(pageResult.getRecords());
        List<TyHrTeacherProfileRespDTO> records = pageResult.getRecords().stream()
            .map(entity -> toRespDTO(entity, userMap))
            .collect(Collectors.toList());
        return PageResultDTO.of(pageResult.getTotal(), records);
    }

    @Override
    public boolean save(TyHrTeacherProfile entity) {
        if (Objects.isNull(entity)) {
            return false;
        }
        entity.setId(null);
        if (Objects.isNull(entity.getIsDeleted())) {
            entity.setIsDeleted(0);
        }
        return tyHrTeacherProfileRepository.save(entity) > 0;
    }

    @Override
    public boolean updateById(TyHrTeacherProfile entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId())) {
            return false;
        }
        return tyHrTeacherProfileRepository.updateById(entity) > 0;
    }

    @Override
    public boolean deleteById(Long id, Long operatorId) {
        if (Objects.isNull(id)) {
            return false;
        }
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return tyHrTeacherProfileRepository.logicDeleteById(id, actualOperatorId) > 0;
    }

    private Map<Long, TySysUser> buildUserMap(List<TyHrTeacherProfile> profiles) {
        if (Objects.isNull(profiles) || profiles.isEmpty()) {
            return Collections.emptyMap();
        }
        List<Long> userIds = profiles.stream()
            .map(TyHrTeacherProfile::getUserId)
            .filter(Objects::nonNull)
            .distinct()
            .collect(Collectors.toList());
        if (userIds.isEmpty()) {
            return Collections.emptyMap();
        }
        List<TySysUser> users = tySysUserRepository.list(
            Wrappers.<TySysUser>lambdaQuery()
                .in(TySysUser::getId, userIds)
                .eq(TySysUser::getIsDeleted, 0)
        );
        return users.stream()
            .collect(Collectors.toMap(TySysUser::getId, Function.identity(), (left, right) -> left));
    }

    private TyHrTeacherProfileRespDTO toRespDTO(TyHrTeacherProfile entity, Map<Long, TySysUser> userMap) {
        TyHrTeacherProfileRespDTO respDTO = new TyHrTeacherProfileRespDTO();
        BeanUtils.copyProperties(entity, respDTO);
        TySysUser user = userMap.get(entity.getUserId());
        if (Objects.nonNull(user)) {
            respDTO.setUsername(user.getUsername());
            respDTO.setRealName(user.getRealName());
            respDTO.setMobile(user.getMobile());
            respDTO.setJobNo(user.getJobNo());
        }
        return respDTO;
    }
}


