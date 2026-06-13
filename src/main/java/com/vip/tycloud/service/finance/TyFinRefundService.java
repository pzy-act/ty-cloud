package com.vip.tycloud.service.finance;

import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.finance.TyFinRefund;

/**
 * 收费与订单 功能模块 - 退款记录 - 服务接口。
 */
public interface TyFinRefundService {

    /**
     * 按主键查询。
     *
     * @param id 主键ID
     * @return 实体对象
     */
    TyFinRefund getById(Long id);

    /**
     * 分页查询。
     *
     * @param pageNumber 页码
     * @param pageSize 每页条数
     * @return 分页结果
     */
    PageResultDTO<TyFinRefund> page(Integer pageNumber, Integer pageSize, String keyword, Integer status);

    /**
     * 新增数据。
     *
     * @param entity 实体对象
     * @return 是否成功
     */
    boolean save(TyFinRefund entity);

    /**
     * 按主键更新。
     *
     * @param entity 实体对象
     * @return 是否成功
     */
    boolean updateById(TyFinRefund entity);

    /**
     * 逻辑删除。
     *
     * @param id 主键ID
     * @param operatorId 操作人ID
     * @return 是否成功
     */
    boolean deleteById(Long id, Long operatorId);
}


