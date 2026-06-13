package com.vip.tycloud.controller.inventory;

import com.vip.tycloud.common.dto.ApiResponse;
import com.vip.tycloud.common.dto.PageFilterReqDTO;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.dto.inventory.TyInvPurchaseOrderCreateReqDTO;
import com.vip.tycloud.dto.inventory.TyInvPurchaseOrderRespDTO;
import com.vip.tycloud.dto.inventory.TyInvPurchaseOrderUpdateReqDTO;
import com.vip.tycloud.entity.inventory.TyInvPurchaseOrder;
import com.vip.tycloud.service.inventory.TyInvPurchaseOrderService;
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
 * 耗材与库存 功能模块 - 采购单 - 控制器。
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventory/purchase-order")
public class TyInvPurchaseOrderController {

    private final TyInvPurchaseOrderService tyInvPurchaseOrderService;

    /**
     * 新增数据。
     *
     * @param req 请求参数
     * @return 是否成功
     */
    @PostMapping("/create")
    public ApiResponse<Boolean> create(@Valid @RequestBody TyInvPurchaseOrderCreateReqDTO req) {
        return ApiResponse.success(tyInvPurchaseOrderService.save(toEntity(req)));
    }

    /**
     * 更新数据。
     *
     * @param req 请求参数
     * @return 是否成功
     */
    @PostMapping("/update")
    public ApiResponse<Boolean> update(@Valid @RequestBody TyInvPurchaseOrderUpdateReqDTO req) {
        return ApiResponse.success(tyInvPurchaseOrderService.updateById(toEntity(req)));
    }

    /**
     * 查询详情。
     *
     * @param id 主键ID
     * @return 详情
     */
    @GetMapping("/detail/{id}")
    public ApiResponse<TyInvPurchaseOrderRespDTO> detail(@PathVariable Long id) {
        return ApiResponse.success(toRespDTO(tyInvPurchaseOrderService.getById(id)));
    }

    /**
     * 分页查询。
     *
     * @param req 分页参数
     * @return 分页结果
     */
    @PostMapping("/page")
    public ApiResponse<PageResultDTO<TyInvPurchaseOrderRespDTO>> page(@Valid @RequestBody PageFilterReqDTO req) {
        PageResultDTO<TyInvPurchaseOrder> pageResult = tyInvPurchaseOrderService.page(req.getPageNumber(), req.getPageSize(), req.getKeyword(), req.getStatus());
        List<TyInvPurchaseOrderRespDTO> records = pageResult.getRecords().stream()
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
        return ApiResponse.success(tyInvPurchaseOrderService.deleteById(id, actualOperatorId));
    }

    private TyInvPurchaseOrder toEntity(TyInvPurchaseOrderCreateReqDTO req) {
        TyInvPurchaseOrder entity = new TyInvPurchaseOrder();
        BeanUtils.copyProperties(req, entity);
        return entity;
    }

    private TyInvPurchaseOrder toEntity(TyInvPurchaseOrderUpdateReqDTO req) {
        TyInvPurchaseOrder entity = new TyInvPurchaseOrder();
        BeanUtils.copyProperties(req, entity);
        return entity;
    }

    private TyInvPurchaseOrderRespDTO toRespDTO(TyInvPurchaseOrder entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        TyInvPurchaseOrderRespDTO respDTO = new TyInvPurchaseOrderRespDTO();
        BeanUtils.copyProperties(entity, respDTO);
        return respDTO;
    }
}


