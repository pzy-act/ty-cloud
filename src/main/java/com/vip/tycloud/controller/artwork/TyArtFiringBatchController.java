package com.vip.tycloud.controller.artwork;

import com.vip.tycloud.common.dto.ApiResponse;
import com.vip.tycloud.common.dto.PageQueryReqDTO;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.dto.artwork.TyArtFiringBatchCreateReqDTO;
import com.vip.tycloud.dto.artwork.TyArtFiringBatchRespDTO;
import com.vip.tycloud.dto.artwork.TyArtFiringBatchUpdateReqDTO;
import com.vip.tycloud.entity.artwork.TyArtFiringBatch;
import com.vip.tycloud.service.artwork.TyArtFiringBatchService;
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
 * 陶艺作品管理 功能模块 - 烧制批次 - 控制器。
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/artwork/firing-batch")
public class TyArtFiringBatchController {

    private final TyArtFiringBatchService tyArtFiringBatchService;

    /**
     * 新增数据。
     *
     * @param req 请求参数
     * @return 是否成功
     */
    @PostMapping("/create")
    public ApiResponse<Boolean> create(@Valid @RequestBody TyArtFiringBatchCreateReqDTO req) {
        return ApiResponse.success(tyArtFiringBatchService.save(toEntity(req)));
    }

    /**
     * 更新数据。
     *
     * @param req 请求参数
     * @return 是否成功
     */
    @PostMapping("/update")
    public ApiResponse<Boolean> update(@Valid @RequestBody TyArtFiringBatchUpdateReqDTO req) {
        return ApiResponse.success(tyArtFiringBatchService.updateById(toEntity(req)));
    }

    /**
     * 查询详情。
     *
     * @param id 主键ID
     * @return 详情
     */
    @GetMapping("/detail/{id}")
    public ApiResponse<TyArtFiringBatchRespDTO> detail(@PathVariable Long id) {
        return ApiResponse.success(toRespDTO(tyArtFiringBatchService.getById(id)));
    }

    /**
     * 分页查询。
     *
     * @param req 分页参数
     * @return 分页结果
     */
    @PostMapping("/page")
    public ApiResponse<PageResultDTO<TyArtFiringBatchRespDTO>> page(@Valid @RequestBody PageQueryReqDTO req) {
        PageResultDTO<TyArtFiringBatch> pageResult = tyArtFiringBatchService.page(req.getPageNumber(), req.getPageSize());
        List<TyArtFiringBatchRespDTO> records = pageResult.getRecords().stream()
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
        return ApiResponse.success(tyArtFiringBatchService.deleteById(id, actualOperatorId));
    }

    private TyArtFiringBatch toEntity(TyArtFiringBatchCreateReqDTO req) {
        TyArtFiringBatch entity = new TyArtFiringBatch();
        BeanUtils.copyProperties(req, entity);
        return entity;
    }

    private TyArtFiringBatch toEntity(TyArtFiringBatchUpdateReqDTO req) {
        TyArtFiringBatch entity = new TyArtFiringBatch();
        BeanUtils.copyProperties(req, entity);
        return entity;
    }

    private TyArtFiringBatchRespDTO toRespDTO(TyArtFiringBatch entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        TyArtFiringBatchRespDTO respDTO = new TyArtFiringBatchRespDTO();
        BeanUtils.copyProperties(entity, respDTO);
        return respDTO;
    }
}


