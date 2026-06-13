package com.vip.tycloud.controller.contract;

import com.vip.tycloud.common.dto.ApiResponse;
import com.vip.tycloud.common.dto.PageFilterReqDTO;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.dto.contract.TyBizContractCreateReqDTO;
import com.vip.tycloud.dto.contract.TyBizContractRespDTO;
import com.vip.tycloud.dto.contract.TyBizContractUpdateReqDTO;
import com.vip.tycloud.entity.contract.TyBizContract;
import com.vip.tycloud.service.contract.TyBizContractService;
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
 * 报名与合同 功能模块 - 报名合同 - 控制器。
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/contract/contract")
public class TyBizContractController {

    private final TyBizContractService tyBizContractService;

    /**
     * 新增数据。
     *
     * @param req 请求参数
     * @return 是否成功
     */
    @PostMapping("/create")
    public ApiResponse<Boolean> create(@Valid @RequestBody TyBizContractCreateReqDTO req) {
        return ApiResponse.success(tyBizContractService.save(toEntity(req)));
    }

    /**
     * 更新数据。
     *
     * @param req 请求参数
     * @return 是否成功
     */
    @PostMapping("/update")
    public ApiResponse<Boolean> update(@Valid @RequestBody TyBizContractUpdateReqDTO req) {
        return ApiResponse.success(tyBizContractService.updateById(toEntity(req)));
    }

    /**
     * 查询详情。
     *
     * @param id 主键ID
     * @return 详情
     */
    @GetMapping("/detail/{id}")
    public ApiResponse<TyBizContractRespDTO> detail(@PathVariable Long id) {
        return ApiResponse.success(toRespDTO(tyBizContractService.getById(id)));
    }

    /**
     * 分页查询。
     *
     * @param req 分页参数
     * @return 分页结果
     */
    @PostMapping("/page")
    public ApiResponse<PageResultDTO<TyBizContractRespDTO>> page(@Valid @RequestBody PageFilterReqDTO req) {
        PageResultDTO<TyBizContract> pageResult = tyBizContractService.page(
            req.getPageNumber(),
            req.getPageSize(),
            req.getKeyword(),
            req.getStatus()
        );
        List<TyBizContractRespDTO> records = pageResult.getRecords().stream()
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
        return ApiResponse.success(tyBizContractService.deleteById(id, actualOperatorId));
    }

    private TyBizContract toEntity(TyBizContractCreateReqDTO req) {
        TyBizContract entity = new TyBizContract();
        BeanUtils.copyProperties(req, entity);
        return entity;
    }

    private TyBizContract toEntity(TyBizContractUpdateReqDTO req) {
        TyBizContract entity = new TyBizContract();
        BeanUtils.copyProperties(req, entity);
        return entity;
    }

    private TyBizContractRespDTO toRespDTO(TyBizContract entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        TyBizContractRespDTO respDTO = new TyBizContractRespDTO();
        BeanUtils.copyProperties(entity, respDTO);
        return respDTO;
    }
}


