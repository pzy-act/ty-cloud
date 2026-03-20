package com.vip.tycloud.controller.course;

import com.vip.tycloud.common.dto.ApiResponse;
import com.vip.tycloud.common.dto.PageQueryReqDTO;
import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.dto.course.TyEduCoursePackageCreateReqDTO;
import com.vip.tycloud.dto.course.TyEduCoursePackageRespDTO;
import com.vip.tycloud.dto.course.TyEduCoursePackageUpdateReqDTO;
import com.vip.tycloud.entity.course.TyEduCoursePackage;
import com.vip.tycloud.service.course.TyEduCoursePackageService;
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
 * 课程与班级 功能模块 - 课包管理 - 控制器。
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/course/course-package")
public class TyEduCoursePackageController {

    private final TyEduCoursePackageService tyEduCoursePackageService;

    /**
     * 新增数据。
     *
     * @param req 请求参数
     * @return 是否成功
     */
    @PostMapping("/create")
    public ApiResponse<Boolean> create(@Valid @RequestBody TyEduCoursePackageCreateReqDTO req) {
        return ApiResponse.success(tyEduCoursePackageService.save(toEntity(req)));
    }

    /**
     * 更新数据。
     *
     * @param req 请求参数
     * @return 是否成功
     */
    @PostMapping("/update")
    public ApiResponse<Boolean> update(@Valid @RequestBody TyEduCoursePackageUpdateReqDTO req) {
        return ApiResponse.success(tyEduCoursePackageService.updateById(toEntity(req)));
    }

    /**
     * 查询详情。
     *
     * @param id 主键ID
     * @return 详情
     */
    @GetMapping("/detail/{id}")
    public ApiResponse<TyEduCoursePackageRespDTO> detail(@PathVariable Long id) {
        return ApiResponse.success(toRespDTO(tyEduCoursePackageService.getById(id)));
    }

    /**
     * 分页查询。
     *
     * @param req 分页参数
     * @return 分页结果
     */
    @PostMapping("/page")
    public ApiResponse<PageResultDTO<TyEduCoursePackageRespDTO>> page(@Valid @RequestBody PageQueryReqDTO req) {
        PageResultDTO<TyEduCoursePackage> pageResult = tyEduCoursePackageService.page(req.getPageNumber(), req.getPageSize());
        List<TyEduCoursePackageRespDTO> records = pageResult.getRecords().stream()
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
        return ApiResponse.success(tyEduCoursePackageService.deleteById(id, actualOperatorId));
    }

    private TyEduCoursePackage toEntity(TyEduCoursePackageCreateReqDTO req) {
        TyEduCoursePackage entity = new TyEduCoursePackage();
        BeanUtils.copyProperties(req, entity);
        return entity;
    }

    private TyEduCoursePackage toEntity(TyEduCoursePackageUpdateReqDTO req) {
        TyEduCoursePackage entity = new TyEduCoursePackage();
        BeanUtils.copyProperties(req, entity);
        return entity;
    }

    private TyEduCoursePackageRespDTO toRespDTO(TyEduCoursePackage entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        TyEduCoursePackageRespDTO respDTO = new TyEduCoursePackageRespDTO();
        BeanUtils.copyProperties(entity, respDTO);
        return respDTO;
    }
}


