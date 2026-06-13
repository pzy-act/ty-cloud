package com.vip.tycloud.service.student;

import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.student.TyCrmLeadFollow;
import java.util.List;

/**
 * 学员管理 功能模块 - 线索跟进 - 服务接口。
 */
public interface TyCrmLeadFollowService {

    /**
     * 按主键查询。
     *
     * @param id 主键ID
     * @return 实体对象
     */
    TyCrmLeadFollow getById(Long id);

    /**
     * 分页查询。
     *
     * @param pageNumber 页码
     * @param pageSize 每页条数
     * @return 分页结果
     */
    PageResultDTO<TyCrmLeadFollow> page(Integer pageNumber, Integer pageSize);

    /**
     * Query follow records by lead id.
     *
     * @param leadId lead id
     * @return follow records
     */
    List<TyCrmLeadFollow> listByLeadId(Long leadId);

    /**
     * 新增数据。
     *
     * @param entity 实体对象
     * @return 是否成功
     */
    boolean save(TyCrmLeadFollow entity);

    /**
     * 按主键更新。
     *
     * @param entity 实体对象
     * @return 是否成功
     */
    boolean updateById(TyCrmLeadFollow entity);

    /**
     * 逻辑删除。
     *
     * @param id 主键ID
     * @param operatorId 操作人ID
     * @return 是否成功
     */
    boolean deleteById(Long id, Long operatorId);
}


