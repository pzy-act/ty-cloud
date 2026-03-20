package com.vip.tycloud.controller.system;

import com.vip.tycloud.common.dto.ApiResponse;
import com.vip.tycloud.common.dto.PageQueryReqDTO;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.dto.system.TySysUserCreateReqDTO;
import com.vip.tycloud.dto.system.TySysUserRespDTO;
import com.vip.tycloud.dto.system.TySysUserUpdateReqDTO;
import com.vip.tycloud.entity.system.TySysUser;
import com.vip.tycloud.service.system.TySysUserService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 组织与权限 功能模块 - 用户管理 - 控制器。
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/system/user")
public class TySysUserController {

    private final TySysUserService tySysUserService;

    /**
     * 新增数据。
     *
     * @param req 请求参数
     * @return 是否成功
     */
    @PostMapping("/create")
    public ApiResponse<Boolean> create(@Valid @RequestBody TySysUserCreateReqDTO req) {
        return ApiResponse.success(tySysUserService.save(toEntity(req)));
    }

    /**
     * 更新数据。
     *
     * @param req 请求参数
     * @return 是否成功
     */
    @PostMapping("/update")
    public ApiResponse<Boolean> update(@Valid @RequestBody TySysUserUpdateReqDTO req) {
        return ApiResponse.success(tySysUserService.updateById(toEntity(req)));
    }

    /**
     * 查询详情。
     *
     * @param id 主键ID
     * @return 详情
     */
    @GetMapping("/detail/{id}")
    public ApiResponse<TySysUserRespDTO> detail(@PathVariable Long id) {
        return ApiResponse.success(toRespDTO(tySysUserService.getById(id)));
    }

    /**
     * 分页查询。
     *
     * @param req 分页参数
     * @return 分页结果
     */
    @PostMapping("/page")
    public ApiResponse<PageResultDTO<TySysUserRespDTO>> page(@Valid @RequestBody PageQueryReqDTO req) {
        PageResultDTO<TySysUser> pageResult = tySysUserService.page(req.getPageNumber(), req.getPageSize());
        List<TySysUserRespDTO> records = pageResult.getRecords().stream()
            .map(this::toRespDTO)
            .collect(Collectors.toList());
        return ApiResponse.success(PageResultDTO.of(pageResult.getTotal(), records));
    }

    /**
     * 逻辑删除。
     *
     * @param id 主键ID
     * @param operatorId 操作人ID
     * @return 是否成功
     */
    @DeleteMapping("/{id}")
    public ApiResponse<Boolean> delete(
        @PathVariable Long id,
        @RequestParam(value = "operatorId", required = false) Long operatorId
    ) {
        Long actualOperatorId = Objects.isNull(operatorId) ? 0L : operatorId;
        return ApiResponse.success(tySysUserService.deleteById(id, actualOperatorId));
    }

    private TySysUser toEntity(TySysUserCreateReqDTO req) {
        TySysUser entity = new TySysUser();
        BeanUtils.copyProperties(req, entity);
        return entity;
    }

    private TySysUser toEntity(TySysUserUpdateReqDTO req) {
        TySysUser entity = new TySysUser();
        BeanUtils.copyProperties(req, entity);
        return entity;
    }

    private TySysUserRespDTO toRespDTO(TySysUser entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        TySysUserRespDTO respDTO = new TySysUserRespDTO();
        BeanUtils.copyProperties(entity, respDTO);
        return respDTO;
    }
}


