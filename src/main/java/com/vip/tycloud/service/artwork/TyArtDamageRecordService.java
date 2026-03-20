package com.vip.tycloud.service.artwork;

import com.vip.tycloud.common.dto.PageResultDTO;
import com.vip.tycloud.entity.artwork.TyArtDamageRecord;

/**
 * 陶艺作品管理 功能模块 - 破损记录 - 服务接口。
 */
public interface TyArtDamageRecordService {

    /**
     * 按主键查询。
     *
     * @param id 主键ID
     * @return 实体对象
     */
    TyArtDamageRecord getById(Long id);

    /**
     * 分页查询。
     *
     * @param pageNumber 页码
     * @param pageSize 每页条数
     * @return 分页结果
     */
    PageResultDTO<TyArtDamageRecord> page(Integer pageNumber, Integer pageSize);

    /**
     * 新增数据。
     *
     * @param entity 实体对象
     * @return 是否成功
     */
    boolean save(TyArtDamageRecord entity);

    /**
     * 按主键更新。
     *
     * @param entity 实体对象
     * @return 是否成功
     */
    boolean updateById(TyArtDamageRecord entity);

    /**
     * 逻辑删除。
     *
     * @param id 主键ID
     * @param operatorId 操作人ID
     * @return 是否成功
     */
    boolean deleteById(Long id, Long operatorId);
}


