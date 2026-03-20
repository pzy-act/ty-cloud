package com.vip.tycloud.controller.finance;

import com.vip.tycloud.common.dto.ApiResponse;
import com.vip.tycloud.common.dto.PageQueryReqDTO;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.dto.finance.TyFinInvoiceCreateReqDTO;
import com.vip.tycloud.dto.finance.TyFinInvoiceRespDTO;
import com.vip.tycloud.dto.finance.TyFinInvoiceUpdateReqDTO;
import com.vip.tycloud.entity.finance.TyFinInvoice;
import com.vip.tycloud.service.finance.TyFinInvoiceService;
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
 * 收费与订单 功能模块 - 发票管理 - 控制器。
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/finance/invoice")
public class TyFinInvoiceController {

    private final TyFinInvoiceService tyFinInvoiceService;

    /**
     * 新增数据。
     *
     * @param req 请求参数
     * @return 是否成功
     */
    @PostMapping("/create")
    public ApiResponse<Boolean> create(@Valid @RequestBody TyFinInvoiceCreateReqDTO req) {
        return ApiResponse.success(tyFinInvoiceService.save(toEntity(req)));
    }

    /**
     * 更新数据。
     *
     * @param req 请求参数
     * @return 是否成功
     */
    @PostMapping("/update")
    public ApiResponse<Boolean> update(@Valid @RequestBody TyFinInvoiceUpdateReqDTO req) {
        return ApiResponse.success(tyFinInvoiceService.updateById(toEntity(req)));
    }

    /**
     * 查询详情。
     *
     * @param id 主键ID
     * @return 详情
     */
    @GetMapping("/detail/{id}")
    public ApiResponse<TyFinInvoiceRespDTO> detail(@PathVariable Long id) {
        return ApiResponse.success(toRespDTO(tyFinInvoiceService.getById(id)));
    }

    /**
     * 分页查询。
     *
     * @param req 分页参数
     * @return 分页结果
     */
    @PostMapping("/page")
    public ApiResponse<PageResultDTO<TyFinInvoiceRespDTO>> page(@Valid @RequestBody PageQueryReqDTO req) {
        PageResultDTO<TyFinInvoice> pageResult = tyFinInvoiceService.page(req.getPageNumber(), req.getPageSize());
        List<TyFinInvoiceRespDTO> records = pageResult.getRecords().stream()
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
        return ApiResponse.success(tyFinInvoiceService.deleteById(id, actualOperatorId));
    }

    private TyFinInvoice toEntity(TyFinInvoiceCreateReqDTO req) {
        TyFinInvoice entity = new TyFinInvoice();
        BeanUtils.copyProperties(req, entity);
        return entity;
    }

    private TyFinInvoice toEntity(TyFinInvoiceUpdateReqDTO req) {
        TyFinInvoice entity = new TyFinInvoice();
        BeanUtils.copyProperties(req, entity);
        return entity;
    }

    private TyFinInvoiceRespDTO toRespDTO(TyFinInvoice entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        TyFinInvoiceRespDTO respDTO = new TyFinInvoiceRespDTO();
        BeanUtils.copyProperties(entity, respDTO);
        return respDTO;
    }
}


