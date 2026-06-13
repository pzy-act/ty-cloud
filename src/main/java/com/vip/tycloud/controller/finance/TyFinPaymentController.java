package com.vip.tycloud.controller.finance;

import com.vip.tycloud.common.dto.ApiResponse;
import com.vip.tycloud.common.dto.PageFilterReqDTO;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.dto.finance.TyFinPaymentCreateReqDTO;
import com.vip.tycloud.dto.finance.TyFinPaymentRespDTO;
import com.vip.tycloud.dto.finance.TyFinPaymentUpdateReqDTO;
import com.vip.tycloud.entity.finance.TyFinPayment;
import com.vip.tycloud.service.finance.TyFinPaymentService;
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
 * 收费与订单 功能模块 - 收款记录 - 控制器。
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/finance/payment")
public class TyFinPaymentController {

    private final TyFinPaymentService tyFinPaymentService;

    /**
     * 新增数据。
     *
     * @param req 请求参数
     * @return 是否成功
     */
    @PostMapping("/create")
    public ApiResponse<Boolean> create(@Valid @RequestBody TyFinPaymentCreateReqDTO req) {
        return ApiResponse.success(tyFinPaymentService.save(toEntity(req)));
    }

    /**
     * 更新数据。
     *
     * @param req 请求参数
     * @return 是否成功
     */
    @PostMapping("/update")
    public ApiResponse<Boolean> update(@Valid @RequestBody TyFinPaymentUpdateReqDTO req) {
        return ApiResponse.success(tyFinPaymentService.updateById(toEntity(req)));
    }

    /**
     * 查询详情。
     *
     * @param id 主键ID
     * @return 详情
     */
    @GetMapping("/detail/{id}")
    public ApiResponse<TyFinPaymentRespDTO> detail(@PathVariable Long id) {
        return ApiResponse.success(toRespDTO(tyFinPaymentService.getById(id)));
    }

    /**
     * 分页查询。
     *
     * @param req 分页参数
     * @return 分页结果
     */
    @PostMapping("/page")
    public ApiResponse<PageResultDTO<TyFinPaymentRespDTO>> page(@Valid @RequestBody PageFilterReqDTO req) {
        PageResultDTO<TyFinPayment> pageResult = tyFinPaymentService.page(
            req.getPageNumber(),
            req.getPageSize(),
            req.getKeyword(),
            req.getStatus()
        );
        List<TyFinPaymentRespDTO> records = pageResult.getRecords().stream()
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
        return ApiResponse.success(tyFinPaymentService.deleteById(id, actualOperatorId));
    }

    private TyFinPayment toEntity(TyFinPaymentCreateReqDTO req) {
        TyFinPayment entity = new TyFinPayment();
        BeanUtils.copyProperties(req, entity);
        return entity;
    }

    private TyFinPayment toEntity(TyFinPaymentUpdateReqDTO req) {
        TyFinPayment entity = new TyFinPayment();
        BeanUtils.copyProperties(req, entity);
        return entity;
    }

    private TyFinPaymentRespDTO toRespDTO(TyFinPayment entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        TyFinPaymentRespDTO respDTO = new TyFinPaymentRespDTO();
        BeanUtils.copyProperties(entity, respDTO);
        return respDTO;
    }
}


