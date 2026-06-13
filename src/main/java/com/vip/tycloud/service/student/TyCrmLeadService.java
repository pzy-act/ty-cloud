package com.vip.tycloud.service.student;

import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.student.TyCrmLead;

/**
 * 学员管理 功能模块 - 线索管理 - 服务接口。
 */
public interface TyCrmLeadService {

    /**
     * 按主键查询。
     *
     * @param id 主键ID
     * @return 实体对象
     */
    TyCrmLead getById(Long id);

    /**
     * 分页查询。
     *
     * @param pageNumber 页码
     * @param pageSize 每页条数
     * @return 分页结果
     */
    PageResultDTO<TyCrmLead> page(Integer pageNumber, Integer pageSize);

    /**
     * 新增数据。
     *
     * @param entity 实体对象
     * @return 是否成功
     */
    boolean save(TyCrmLead entity);

    /**
     * 按主键更新。
     *
     * @param entity 实体对象
     * @return 是否成功
     */
    boolean updateById(TyCrmLead entity);

    /**
     * Convert lead to student.
     *
     * @param leadId lead id
     * @return student id
     */
    Long convertToStudent(Long leadId);

    /**
     * 逻辑删除。
     *
     * @param id 主键ID
     * @param operatorId 操作人ID
     * @return 是否成功
     */
    boolean deleteById(Long id, Long operatorId);
}


