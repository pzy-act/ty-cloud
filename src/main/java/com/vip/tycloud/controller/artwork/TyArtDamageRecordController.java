package com.vip.tycloud.controller.artwork;

import com.vip.tycloud.common.dto.ApiResponse;
import com.vip.tycloud.common.dto.PageFilterReqDTO;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.dto.artwork.TyArtDamageRecordCreateReqDTO;
import com.vip.tycloud.dto.artwork.TyArtDamageRecordRespDTO;
import com.vip.tycloud.dto.artwork.TyArtDamageRecordUpdateReqDTO;
import com.vip.tycloud.entity.artwork.TyArtDamageRecord;
import com.vip.tycloud.service.artwork.TyArtDamageRecordService;
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
 * 陶艺作品管理 功能模块 - 破损记录 - 控制器。
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/artwork/damage-record")
public class TyArtDamageRecordController {

    private final TyArtDamageRecordService tyArtDamageRecordService;

    /**
     * 新增数据。
     *
     * @param req 请求参数
     * @return 是否成功
     */
    @PostMapping("/create")
    public ApiResponse<Boolean> create(@Valid @RequestBody TyArtDamageRecordCreateReqDTO req) {
        return ApiResponse.success(tyArtDamageRecordService.save(toEntity(req)));
    }

    /**
     * 更新数据。
     *
     * @param req 请求参数
     * @return 是否成功
     */
    @PostMapping("/update")
    public ApiResponse<Boolean> update(@Valid @RequestBody TyArtDamageRecordUpdateReqDTO req) {
        return ApiResponse.success(tyArtDamageRecordService.updateById(toEntity(req)));
    }

    /**
     * 查询详情。
     *
     * @param id 主键ID
     * @return 详情
     */
    @GetMapping("/detail/{id}")
    public ApiResponse<TyArtDamageRecordRespDTO> detail(@PathVariable Long id) {
        return ApiResponse.success(toRespDTO(tyArtDamageRecordService.getById(id)));
    }

    /**
     * 分页查询。
     *
     * @param req 分页参数
     * @return 分页结果
     */
    @PostMapping("/page")
    public ApiResponse<PageResultDTO<TyArtDamageRecordRespDTO>> page(@Valid @RequestBody PageFilterReqDTO req) {
        PageResultDTO<TyArtDamageRecord> pageResult = tyArtDamageRecordService.page(req.getPageNumber(), req.getPageSize(), req.getKeyword(), req.getStatus());
        List<TyArtDamageRecordRespDTO> records = pageResult.getRecords().stream()
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
        return ApiResponse.success(tyArtDamageRecordService.deleteById(id, actualOperatorId));
    }

    private TyArtDamageRecord toEntity(TyArtDamageRecordCreateReqDTO req) {
        TyArtDamageRecord entity = new TyArtDamageRecord();
        BeanUtils.copyProperties(req, entity);
        return entity;
    }

    private TyArtDamageRecord toEntity(TyArtDamageRecordUpdateReqDTO req) {
        TyArtDamageRecord entity = new TyArtDamageRecord();
        BeanUtils.copyProperties(req, entity);
        return entity;
    }

    private TyArtDamageRecordRespDTO toRespDTO(TyArtDamageRecord entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        TyArtDamageRecordRespDTO respDTO = new TyArtDamageRecordRespDTO();
        BeanUtils.copyProperties(entity, respDTO);
        return respDTO;
    }
}


