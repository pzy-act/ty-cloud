package com.vip.tycloud.controller.inventory;

import com.vip.tycloud.common.dto.ApiResponse;
import com.vip.tycloud.common.dto.PageQueryReqDTO;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.dto.inventory.TyInvSupplierCreateReqDTO;
import com.vip.tycloud.dto.inventory.TyInvSupplierRespDTO;
import com.vip.tycloud.dto.inventory.TyInvSupplierUpdateReqDTO;
import com.vip.tycloud.entity.inventory.TyInvSupplier;
import com.vip.tycloud.service.inventory.TyInvSupplierService;
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
 * 耗材与库存 功能模块 - 供应商管理 - 控制器。
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventory/supplier")
public class TyInvSupplierController {

    private final TyInvSupplierService tyInvSupplierService;

    /**
     * 新增数据。
     *
     * @param req 请求参数
     * @return 是否成功
     */
    @PostMapping("/create")
    public ApiResponse<Boolean> create(@Valid @RequestBody TyInvSupplierCreateReqDTO req) {
        return ApiResponse.success(tyInvSupplierService.save(toEntity(req)));
    }

    /**
     * 更新数据。
     *
     * @param req 请求参数
     * @return 是否成功
     */
    @PostMapping("/update")
    public ApiResponse<Boolean> update(@Valid @RequestBody TyInvSupplierUpdateReqDTO req) {
        return ApiResponse.success(tyInvSupplierService.updateById(toEntity(req)));
    }

    /**
     * 查询详情。
     *
     * @param id 主键ID
     * @return 详情
     */
    @GetMapping("/detail/{id}")
    public ApiResponse<TyInvSupplierRespDTO> detail(@PathVariable Long id) {
        return ApiResponse.success(toRespDTO(tyInvSupplierService.getById(id)));
    }

    /**
     * 分页查询。
     *
     * @param req 分页参数
     * @return 分页结果
     */
    @PostMapping("/page")
    public ApiResponse<PageResultDTO<TyInvSupplierRespDTO>> page(@Valid @RequestBody PageQueryReqDTO req) {
        PageResultDTO<TyInvSupplier> pageResult = tyInvSupplierService.page(req.getPageNumber(), req.getPageSize());
        List<TyInvSupplierRespDTO> records = pageResult.getRecords().stream()
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
        return ApiResponse.success(tyInvSupplierService.deleteById(id, actualOperatorId));
    }

    private TyInvSupplier toEntity(TyInvSupplierCreateReqDTO req) {
        TyInvSupplier entity = new TyInvSupplier();
        BeanUtils.copyProperties(req, entity);
        return entity;
    }

    private TyInvSupplier toEntity(TyInvSupplierUpdateReqDTO req) {
        TyInvSupplier entity = new TyInvSupplier();
        BeanUtils.copyProperties(req, entity);
        return entity;
    }

    private TyInvSupplierRespDTO toRespDTO(TyInvSupplier entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        TyInvSupplierRespDTO respDTO = new TyInvSupplierRespDTO();
        BeanUtils.copyProperties(entity, respDTO);
        return respDTO;
    }
}


