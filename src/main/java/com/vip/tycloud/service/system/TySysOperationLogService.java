package com.vip.tycloud.service.system;

import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.system.TySysOperationLog;

/**
 * 组织与权限 功能模块 - 操作日志 - 服务接口。
 */
public interface TySysOperationLogService {

    /**
     * 按主键查询。
     *
     * @param id 主键ID
     * @return 实体对象
     */
    TySysOperationLog getById(Long id);

    /**
     * 分页查询。
     *
     * @param pageNumber 页码
     * @param pageSize 每页条数
     * @return 分页结果
     */
    PageResultDTO<TySysOperationLog> page(Integer pageNumber, Integer pageSize);

    /**
     * 新增数据。
     *
     * @param entity 实体对象
     * @return 是否成功
     */
    boolean save(TySysOperationLog entity);

    /**
     * 按主键更新。
     *
     * @param entity 实体对象
     * @return 是否成功
     */
    boolean updateById(TySysOperationLog entity);

    /**
     * 逻辑删除。
     *
     * @param id 主键ID
     * @param operatorId 操作人ID
     * @return 是否成功
     */
    boolean deleteById(Long id, Long operatorId);
}


