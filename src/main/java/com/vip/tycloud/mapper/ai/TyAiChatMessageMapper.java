package com.vip.tycloud.mapper.ai;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vip.tycloud.entity.ai.TyAiChatMessage;
import org.apache.ibatis.annotations.Mapper;

/**
 * 智能问答 功能模块 - 消息 - Mapper 接口。
 */
@Mapper
public interface TyAiChatMessageMapper extends BaseMapper<TyAiChatMessage> {
}
