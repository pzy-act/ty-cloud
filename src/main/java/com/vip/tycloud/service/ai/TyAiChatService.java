package com.vip.tycloud.service.ai;

import com.vip.tycloud.dto.ai.TyAiChatMessageReqDTO;
import com.vip.tycloud.dto.ai.TyAiChatMessageRespDTO;
import com.vip.tycloud.dto.ai.TyAiModelRespDTO;
import com.vip.tycloud.dto.ai.TyAiChatSessionCreateReqDTO;
import com.vip.tycloud.dto.ai.TyAiChatSessionRespDTO;
import com.vip.tycloud.dto.ai.TyAiSendMessageRespDTO;
import com.vip.tycloud.common.dto.PageResultDTO;
import java.util.List;

/**
 * 智能问答 功能模块 - 聊天服务接口。
 */
public interface TyAiChatService {

    TyAiChatSessionRespDTO createSession(TyAiChatSessionCreateReqDTO req, Long operatorId, Long campusId);

    PageResultDTO<TyAiChatSessionRespDTO> pageSession(Long userId, Integer pageNumber, Integer pageSize);

    List<TyAiChatMessageRespDTO> listMessages(Long userId, Long sessionId);

    TyAiSendMessageRespDTO sendMessage(TyAiChatMessageReqDTO req, Long userId, Long campusId);

    boolean deleteSession(Long userId, Long sessionId, Long operatorId);

    List<TyAiModelRespDTO> listModels();
}
