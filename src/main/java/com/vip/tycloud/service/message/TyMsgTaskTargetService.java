package com.vip.tycloud.service.message;

import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.message.TyMsgTaskTarget;

/**
 * 通知与互动 功能模块 - 消息任务对象 - 服务接口。
 */
public interface TyMsgTaskTargetService {

    /**
     * 按主键查询。
     *
     * @param id 主键ID
     * @return 实体对象
     */
    TyMsgTaskTarget getById(Long id);

    /**
     * 分页查询。
     *
     * @param pageNumber 页码
     * @param pageSize 每页条数
     * @return 分页结果
     */
    PageResultDTO<TyMsgTaskTarget> page(Integer pageNumber, Integer pageSize);

    /**
     * 新增数据。
     *
     * @param entity 实体对象
     * @return 是否成功
     */
    boolean save(TyMsgTaskTarget entity);

    /**
     * 按主键更新。
     *
     * @param entity 实体对象
     * @return 是否成功
     */
    boolean updateById(TyMsgTaskTarget entity);

    /**
     * 逻辑删除。
     *
     * @param id 主键ID
     * @param operatorId 操作人ID
     * @return 是否成功
     */
    boolean deleteById(Long id, Long operatorId);
}


