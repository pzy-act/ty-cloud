package com.vip.tycloud.controller.system;

import com.vip.tycloud.common.dto.ApiResponse;
import com.vip.tycloud.common.dto.PageQueryReqDTO;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.dto.system.TySysMenuCreateReqDTO;
import com.vip.tycloud.dto.system.TySysMenuRespDTO;
import com.vip.tycloud.dto.system.TySysMenuUpdateReqDTO;
import com.vip.tycloud.entity.system.TySysMenu;
import com.vip.tycloud.service.system.TySysMenuService;
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
 * 组织与权限 功能模块 - 菜单权限 - 控制器。
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/system/menu")
public class TySysMenuController {

    private final TySysMenuService tySysMenuService;

    /**
     * 新增数据。
     *
     * @param req 请求参数
     * @return 是否成功
     */
    @PostMapping("/create")
    public ApiResponse<Boolean> create(@Valid @RequestBody TySysMenuCreateReqDTO req) {
        return ApiResponse.success(tySysMenuService.save(toEntity(req)));
    }

    /**
     * 更新数据。
     *
     * @param req 请求参数
     * @return 是否成功
     */
    @PostMapping("/update")
    public ApiResponse<Boolean> update(@Valid @RequestBody TySysMenuUpdateReqDTO req) {
        return ApiResponse.success(tySysMenuService.updateById(toEntity(req)));
    }

    /**
     * 查询详情。
     *
     * @param id 主键ID
     * @return 详情
     */
    @GetMapping("/detail/{id}")
    public ApiResponse<TySysMenuRespDTO> detail(@PathVariable Long id) {
        return ApiResponse.success(toRespDTO(tySysMenuService.getById(id)));
    }

    /**
     * 分页查询。
     *
     * @param req 分页参数
     * @return 分页结果
     */
    @PostMapping("/page")
    public ApiResponse<PageResultDTO<TySysMenuRespDTO>> page(@Valid @RequestBody PageQueryReqDTO req) {
        PageResultDTO<TySysMenu> pageResult = tySysMenuService.page(req.getPageNumber(), req.getPageSize());
        List<TySysMenuRespDTO> records = pageResult.getRecords().stream()
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
        return ApiResponse.success(tySysMenuService.deleteById(id, actualOperatorId));
    }

    private TySysMenu toEntity(TySysMenuCreateReqDTO req) {
        TySysMenu entity = new TySysMenu();
        BeanUtils.copyProperties(req, entity);
        return entity;
    }

    private TySysMenu toEntity(TySysMenuUpdateReqDTO req) {
        TySysMenu entity = new TySysMenu();
        BeanUtils.copyProperties(req, entity);
        return entity;
    }

    private TySysMenuRespDTO toRespDTO(TySysMenu entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        TySysMenuRespDTO respDTO = new TySysMenuRespDTO();
        BeanUtils.copyProperties(entity, respDTO);
        return respDTO;
    }
}


