package com.vip.tycloud.controller.system;

import com.vip.tycloud.common.dto.ApiResponse;
import com.vip.tycloud.common.dto.PageQueryReqDTO;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.dto.system.TySysRoleCreateReqDTO;
import com.vip.tycloud.dto.system.TySysRoleMenuAssignReqDTO;
import com.vip.tycloud.dto.system.TySysRoleRespDTO;
import com.vip.tycloud.dto.system.TySysRoleUpdateReqDTO;
import com.vip.tycloud.entity.system.TySysRole;
import com.vip.tycloud.service.system.TySysRoleMenuService;
import com.vip.tycloud.service.system.TySysRoleService;
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
 * 组织与权限 功能模块 - 角色管理 - 控制器。
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/system/role")
public class TySysRoleController {

    private final TySysRoleService tySysRoleService;
    private final TySysRoleMenuService tySysRoleMenuService;

    /**
     * 新增数据。
     *
     * @param req 请求参数
     * @return 是否成功
     */
    @PostMapping("/create")
    public ApiResponse<Boolean> create(@Valid @RequestBody TySysRoleCreateReqDTO req) {
        return ApiResponse.success(tySysRoleService.save(toEntity(req)));
    }

    /**
     * 更新数据。
     *
     * @param req 请求参数
     * @return 是否成功
     */
    @PostMapping("/update")
    public ApiResponse<Boolean> update(@Valid @RequestBody TySysRoleUpdateReqDTO req) {
        return ApiResponse.success(tySysRoleService.updateById(toEntity(req)));
    }

    /**
     * 查询详情。
     *
     * @param id 主键ID
     * @return 详情
     */
    @GetMapping("/detail/{id}")
    public ApiResponse<TySysRoleRespDTO> detail(@PathVariable Long id) {
        return ApiResponse.success(toRespDTO(tySysRoleService.getById(id)));
    }

    /**
     * 分页查询。
     *
     * @param req 分页参数
     * @return 分页结果
     */
    @PostMapping("/page")
    public ApiResponse<PageResultDTO<TySysRoleRespDTO>> page(@Valid @RequestBody PageQueryReqDTO req) {
        PageResultDTO<TySysRole> pageResult = tySysRoleService.page(req.getPageNumber(), req.getPageSize());
        List<TySysRoleRespDTO> records = pageResult.getRecords().stream()
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
        return ApiResponse.success(tySysRoleService.deleteById(id, actualOperatorId));
    }

    /**
     * 查询角色已授权菜单 ID。
     *
     * @param roleId 角色 ID
     * @return 菜单 ID 列表
     */
    @GetMapping("/{roleId}/menus")
    public ApiResponse<List<Long>> roleMenus(@PathVariable Long roleId) {
        return ApiResponse.success(tySysRoleMenuService.listMenuIdsByRoleId(roleId));
    }

    /**
     * 保存角色菜单授权。
     *
     * @param req 授权参数
     * @return 是否成功
     */
    @PostMapping("/assign-menus")
    public ApiResponse<Boolean> assignMenus(@Valid @RequestBody TySysRoleMenuAssignReqDTO req) {
        Long actualOperatorId = Objects.isNull(req.getOperatorId()) ? 0L : req.getOperatorId();
        return ApiResponse.success(
            tySysRoleMenuService.assignMenus(req.getRoleId(), req.getMenuIds(), actualOperatorId)
        );
    }

    private TySysRole toEntity(TySysRoleCreateReqDTO req) {
        TySysRole entity = new TySysRole();
        BeanUtils.copyProperties(req, entity);
        return entity;
    }

    private TySysRole toEntity(TySysRoleUpdateReqDTO req) {
        TySysRole entity = new TySysRole();
        BeanUtils.copyProperties(req, entity);
        return entity;
    }

    private TySysRoleRespDTO toRespDTO(TySysRole entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        TySysRoleRespDTO respDTO = new TySysRoleRespDTO();
        BeanUtils.copyProperties(entity, respDTO);
        return respDTO;
    }
}


