package com.vip.tycloud.service.teacher;

import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.teacher.TyHrTeacherPayroll;

/**
 * 教师与绩效 功能模块 - 工资核算 - 服务接口。
 */
public interface TyHrTeacherPayrollService {

    /**
     * 按主键查询。
     *
     * @param id 主键ID
     * @return 实体对象
     */
    TyHrTeacherPayroll getById(Long id);

    /**
     * 分页查询。
     *
     * @param pageNumber 页码
     * @param pageSize 每页条数
     * @return 分页结果
     */
    PageResultDTO<TyHrTeacherPayroll> page(Integer pageNumber, Integer pageSize);

    /**
     * 新增数据。
     *
     * @param entity 实体对象
     * @return 是否成功
     */
    boolean save(TyHrTeacherPayroll entity);

    /**
     * 按主键更新。
     *
     * @param entity 实体对象
     * @return 是否成功
     */
    boolean updateById(TyHrTeacherPayroll entity);

    /**
     * 逻辑删除。
     *
     * @param id 主键ID
     * @param operatorId 操作人ID
     * @return 是否成功
     */
    boolean deleteById(Long id, Long operatorId);
}


