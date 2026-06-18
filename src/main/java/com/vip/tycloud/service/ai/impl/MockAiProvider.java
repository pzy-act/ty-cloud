package com.vip.tycloud.service.ai.impl;

import com.vip.tycloud.service.ai.provider.AiChatRequest;
import com.vip.tycloud.service.ai.provider.AiChatResponse;
import com.vip.tycloud.service.ai.provider.AiProvider;
import java.util.Objects;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * AI 模块 - 模拟模型 Provider。
 */
@Component
@ConditionalOnProperty(prefix = "ty.ai.openai", name = "enabled", havingValue = "false", matchIfMissing = true)
public class MockAiProvider implements AiProvider {

    @Override
    public AiChatResponse chat(AiChatRequest request) {
        String latest = "";
        if (Objects.nonNull(request) && !CollectionUtils.isEmpty(request.getMessages())) {
            int lastIndex = request.getMessages().size() - 1;
            latest = request.getMessages().get(lastIndex).getContent();
        }

        StringBuilder contentBuilder = new StringBuilder();
        contentBuilder.append("已收到你的问题：").append(StringUtils.hasText(latest) ? latest : "（空问题）").append('\n');
        contentBuilder.append('\n');
        contentBuilder.append("模拟回复：\n");
        contentBuilder.append("1. 这是当前模块的演示回复，不是最终接入模型的正式结果。\n");
        contentBuilder.append("2. 这里故意返回较长文本，方便前端展示逐步显现、换行和复制等交互。\n");
        contentBuilder.append("3. 后续接入真实 Codex/OpenAI 时，这里会替换成流式输出，而不是一次性返回整段。\n");
        contentBuilder.append("4. 当前页面已经预留会话、消息、额度和删除等主流程，适合继续扩展知识库问答。\n");
        contentBuilder.append('\n');
        contentBuilder.append("建议下一步：\n");
        contentBuilder.append("- 接入真正的 SSE 流式推送。\n");
        contentBuilder.append("- 把知识库检索结果拼进上下文。\n");
        contentBuilder.append("- 给管理员增加额度分配页。\n");
        contentBuilder.append("- 增加消息重试、停止生成和引用来源显示。\n");

        AiChatResponse response = new AiChatResponse();
        String content = contentBuilder.toString();
        response.setContent(content);
        response.setPromptTokens(Math.max(12, content.length() / 5));
        response.setCompletionTokens(Math.max(24, content.length() / 2));
        response.setTotalTokens(response.getPromptTokens() + response.getCompletionTokens());
        response.setFinishReason("stop");
        return response;
    }
}
