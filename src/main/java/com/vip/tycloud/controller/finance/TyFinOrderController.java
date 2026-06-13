package com.vip.tycloud.controller.finance;

import com.vip.tycloud.common.dto.ApiResponse;
import com.vip.tycloud.common.dto.PageFilterReqDTO;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.dto.finance.TyFinOrderCreateReqDTO;
import com.vip.tycloud.dto.finance.TyFinOrderRespDTO;
import com.vip.tycloud.dto.finance.TyFinOrderUpdateReqDTO;
import com.vip.tycloud.entity.finance.TyFinOrder;
import com.vip.tycloud.service.finance.TyFinOrderService;
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
 * 收费与订单 功能模块 - 收费订单 - 控制器。
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/finance/order")
public class TyFinOrderController {

    private final TyFinOrderService tyFinOrderService;

    /**
     * 新增数据。
     *
     * @param req 请求参数
     * @return 是否成功
     */
    @PostMapping("/create")
    public ApiResponse<Boolean> create(@Valid @RequestBody TyFinOrderCreateReqDTO req) {
        return ApiResponse.success(tyFinOrderService.save(toEntity(req)));
    }

    /**
     * 更新数据。
     *
     * @param req 请求参数
     * @return 是否成功
     */
    @PostMapping("/update")
    public ApiResponse<Boolean> update(@Valid @RequestBody TyFinOrderUpdateReqDTO req) {
        return ApiResponse.success(tyFinOrderService.updateById(toEntity(req)));
    }

    /**
     * 查询详情。
     *
     * @param id 主键ID
     * @return 详情
     */
    @GetMapping("/detail/{id}")
    public ApiResponse<TyFinOrderRespDTO> detail(@PathVariable Long id) {
        return ApiResponse.success(toRespDTO(tyFinOrderService.getById(id)));
    }

    /**
     * 分页查询。
     *
     * @param req 分页参数
     * @return 分页结果
     */
    @PostMapping("/page")
    public ApiResponse<PageResultDTO<TyFinOrderRespDTO>> page(@Valid @RequestBody PageFilterReqDTO req) {
        PageResultDTO<TyFinOrder> pageResult = tyFinOrderService.page(
            req.getPageNumber(),
            req.getPageSize(),
            req.getKeyword(),
            req.getStatus()
        );
        List<TyFinOrderRespDTO> records = pageResult.getRecords().stream()
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
        return ApiResponse.success(tyFinOrderService.deleteById(id, actualOperatorId));
    }

    private TyFinOrder toEntity(TyFinOrderCreateReqDTO req) {
        TyFinOrder entity = new TyFinOrder();
        BeanUtils.copyProperties(req, entity);
        return entity;
    }

    private TyFinOrder toEntity(TyFinOrderUpdateReqDTO req) {
        TyFinOrder entity = new TyFinOrder();
        BeanUtils.copyProperties(req, entity);
        return entity;
    }

    private TyFinOrderRespDTO toRespDTO(TyFinOrder entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        TyFinOrderRespDTO respDTO = new TyFinOrderRespDTO();
        BeanUtils.copyProperties(entity, respDTO);
        return respDTO;
    }
}


