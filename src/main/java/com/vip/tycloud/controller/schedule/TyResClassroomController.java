package com.vip.tycloud.controller.schedule;

import com.vip.tycloud.common.dto.ApiResponse;
import com.vip.tycloud.common.dto.PageQueryReqDTO;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.dto.schedule.TyResClassroomCreateReqDTO;
import com.vip.tycloud.dto.schedule.TyResClassroomRespDTO;
import com.vip.tycloud.dto.schedule.TyResClassroomUpdateReqDTO;
import com.vip.tycloud.entity.schedule.TyResClassroom;
import com.vip.tycloud.service.schedule.TyResClassroomService;
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
 * 排课与资源 功能模块 - 教室资源 - 控制器。
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedule/classroom")
public class TyResClassroomController {

    private final TyResClassroomService tyResClassroomService;

    /**
     * 新增数据。
     *
     * @param req 请求参数
     * @return 是否成功
     */
    @PostMapping("/create")
    public ApiResponse<Boolean> create(@Valid @RequestBody TyResClassroomCreateReqDTO req) {
        return ApiResponse.success(tyResClassroomService.save(toEntity(req)));
    }

    /**
     * 更新数据。
     *
     * @param req 请求参数
     * @return 是否成功
     */
    @PostMapping("/update")
    public ApiResponse<Boolean> update(@Valid @RequestBody TyResClassroomUpdateReqDTO req) {
        return ApiResponse.success(tyResClassroomService.updateById(toEntity(req)));
    }

    /**
     * 查询详情。
     *
     * @param id 主键ID
     * @return 详情
     */
    @GetMapping("/detail/{id}")
    public ApiResponse<TyResClassroomRespDTO> detail(@PathVariable Long id) {
        return ApiResponse.success(toRespDTO(tyResClassroomService.getById(id)));
    }

    /**
     * 分页查询。
     *
     * @param req 分页参数
     * @return 分页结果
     */
    @PostMapping("/page")
    public ApiResponse<PageResultDTO<TyResClassroomRespDTO>> page(@Valid @RequestBody PageQueryReqDTO req) {
        PageResultDTO<TyResClassroom> pageResult = tyResClassroomService.page(req.getPageNumber(), req.getPageSize());
        List<TyResClassroomRespDTO> records = pageResult.getRecords().stream()
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
        return ApiResponse.success(tyResClassroomService.deleteById(id, actualOperatorId));
    }

    private TyResClassroom toEntity(TyResClassroomCreateReqDTO req) {
        TyResClassroom entity = new TyResClassroom();
        BeanUtils.copyProperties(req, entity);
        return entity;
    }

    private TyResClassroom toEntity(TyResClassroomUpdateReqDTO req) {
        TyResClassroom entity = new TyResClassroom();
        BeanUtils.copyProperties(req, entity);
        return entity;
    }

    private TyResClassroomRespDTO toRespDTO(TyResClassroom entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        TyResClassroomRespDTO respDTO = new TyResClassroomRespDTO();
        BeanUtils.copyProperties(entity, respDTO);
        return respDTO;
    }
}


