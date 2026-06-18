package com.vip.tycloud.service.ai;

import com.vip.tycloud.dto.ai.TyAiQuotaRespDTO;
import java.util.List;

/**
 * 智能问答 功能模块 - 额度管理 - 服务接口。
 */
public interface TyAiQuotaService {

    List<TyAiQuotaRespDTO> getMyQuota(Long userId);

    boolean ensureDefaultQuota(Long userId, Long operatorId);

    boolean canConsume(Long userId, Integer questionCount, Integer tokenCount);

    boolean consume(Long userId, Long sessionId, Long messageId, String requestId, Integer promptTokens, Integer completionTokens, Long operatorId);
}
