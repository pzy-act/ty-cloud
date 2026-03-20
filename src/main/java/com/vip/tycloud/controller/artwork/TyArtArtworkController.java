package com.vip.tycloud.controller.artwork;

import com.vip.tycloud.common.dto.ApiResponse;
import com.vip.tycloud.common.dto.PageQueryReqDTO;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.dto.artwork.TyArtArtworkCreateReqDTO;
import com.vip.tycloud.dto.artwork.TyArtArtworkRespDTO;
import com.vip.tycloud.dto.artwork.TyArtArtworkUpdateReqDTO;
import com.vip.tycloud.entity.artwork.TyArtArtwork;
import com.vip.tycloud.service.artwork.TyArtArtworkService;
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
 * 陶艺作品管理 功能模块 - 作品建档 - 控制器。
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/artwork/artwork")
public class TyArtArtworkController {

    private final TyArtArtworkService tyArtArtworkService;

    /**
     * 新增数据。
     *
     * @param req 请求参数
     * @return 是否成功
     */
    @PostMapping("/create")
    public ApiResponse<Boolean> create(@Valid @RequestBody TyArtArtworkCreateReqDTO req) {
        return ApiResponse.success(tyArtArtworkService.save(toEntity(req)));
    }

    /**
     * 更新数据。
     *
     * @param req 请求参数
     * @return 是否成功
     */
    @PostMapping("/update")
    public ApiResponse<Boolean> update(@Valid @RequestBody TyArtArtworkUpdateReqDTO req) {
        return ApiResponse.success(tyArtArtworkService.updateById(toEntity(req)));
    }

    /**
     * 查询详情。
     *
     * @param id 主键ID
     * @return 详情
     */
    @GetMapping("/detail/{id}")
    public ApiResponse<TyArtArtworkRespDTO> detail(@PathVariable Long id) {
        return ApiResponse.success(toRespDTO(tyArtArtworkService.getById(id)));
    }

    /**
     * 分页查询。
     *
     * @param req 分页参数
     * @return 分页结果
     */
    @PostMapping("/page")
    public ApiResponse<PageResultDTO<TyArtArtworkRespDTO>> page(@Valid @RequestBody PageQueryReqDTO req) {
        PageResultDTO<TyArtArtwork> pageResult = tyArtArtworkService.page(req.getPageNumber(), req.getPageSize());
        List<TyArtArtworkRespDTO> records = pageResult.getRecords().stream()
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
        return ApiResponse.success(tyArtArtworkService.deleteById(id, actualOperatorId));
    }

    private TyArtArtwork toEntity(TyArtArtworkCreateReqDTO req) {
        TyArtArtwork entity = new TyArtArtwork();
        BeanUtils.copyProperties(req, entity);
        return entity;
    }

    private TyArtArtwork toEntity(TyArtArtworkUpdateReqDTO req) {
        TyArtArtwork entity = new TyArtArtwork();
        BeanUtils.copyProperties(req, entity);
        return entity;
    }

    private TyArtArtworkRespDTO toRespDTO(TyArtArtwork entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        TyArtArtworkRespDTO respDTO = new TyArtArtworkRespDTO();
        BeanUtils.copyProperties(entity, respDTO);
        return respDTO;
    }
}


