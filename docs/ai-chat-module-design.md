# 智能问答模块设计方案

## 1. 背景与目标

当前 `ty-cloud` 后端为 Spring Boot 3 + Java 17 项目，前端项目位于 `/Users/pzy/Documents/WebstormProjects/ty-cloud-web`，技术栈为 Vue 3 + Vite + Element Plus。

智能问答模块目标是为系统增加类似 ChatGPT 网页版的连续对话能力，并预留后续扩展到企业知识库问答、业务数据问答、用户额度控制、模型切换和审计统计。

第一阶段建议实现：

- 登录用户可创建、查看、删除自己的对话会话。
- 支持连续对话，上下文由后端统一管理。
- 支持流式输出，前端逐字展示模型回复。
- 支持按照用户控制问答次数和 token 额度。
- 后端统一保存会话、消息、调用日志和用量记录。
- 模型密钥只放后端环境变量，前端不能直接访问模型服务。

第二阶段扩展：

- 企业知识库问答，也就是基于内部文档、制度、课程资料、合同模板、运营手册等资料进行检索增强问答。
- 支持知识库权限隔离，例如不同校区、角色、用户只能查询授权范围内的知识。
- 支持业务工具调用，例如查询学员课消、合同、考勤、库存等数据。

## 2. 名词说明

### 2.1 普通智能问答

普通智能问答只依赖模型本身能力和当前会话上下文。用户提问后，后端把系统提示词、历史消息、当前问题发送给模型，模型返回答案。

适合：

- 通用问答
- 文案生成
- 课程话术生成
- 代码解释
- 简单运营建议

限制：模型不知道企业内部私有资料，也不能天然保证答案来自系统真实数据。

### 2.2 企业知识库问答

企业知识库问答通常采用 RAG 方案，RAG 是 Retrieval-Augmented Generation，即“检索增强生成”。

核心流程：

1. 管理员上传企业资料，例如制度文档、课程介绍、常见问题、合同模板、操作手册。
2. 后端把文档解析成小段文本 chunk。
3. 为每个 chunk 生成向量 embedding，保存到向量索引或支持向量检索的数据库。
4. 用户提问时，先用问题去知识库里检索最相关的资料片段。
5. 后端把检索结果和用户问题一起交给模型。
6. 模型基于检索到的企业资料回答，并可返回引用来源。

适合：

- 内部制度问答
- 教务、财务、销售 SOP 查询
- 课程资料问答
- 常见客户问题回复
- 员工培训助手

注意：企业知识库问答不是简单把所有文档塞给模型，而是每次按问题检索最相关内容，再让模型基于这些内容回答。

## 3. 总体架构

建议采用“前端聊天工作台 + 后端 AI 中台模块 + 可替换模型 Provider”的结构。

```text
Vue 前端
  |
  | JWT
  v
Spring Boot 后端
  |
  |-- controller/ai       对外接口：会话、消息、流式输出、额度查询
  |-- service/ai          业务编排：上下文、额度、日志、调用模型
  |-- repository/ai       数据访问
  |-- mapper/ai           MyBatis-Plus Mapper
  |-- entity/ai           会话、消息、额度、知识库实体
  |-- dto/ai              请求和响应 DTO
  |-- config/ai           模型配置、额度配置、SSE 配置
  |
  v
模型 Provider
  |
  |-- OpenAI / Codex 能力
  |-- Mock Provider，用于本地开发和测试
```

设计原则：

- 业务代码不直接依赖具体模型 SDK，统一通过 `AiProvider` 调用。
- 会话、消息、用量必须落库，便于审计、统计和恢复。
- 额度控制在调用模型前完成预检查，模型返回后再按实际 token 回写。
- 流式响应由后端代理，前端只接系统自己的 API。

## 4. 后端包结构建议

```text
src/main/java/com/vip/tycloud
  controller/ai
    TyAiChatController.java
    TyAiQuotaController.java
    TyAiKnowledgeController.java        # 第二阶段
  service/ai
    TyAiChatService.java
    TyAiQuotaService.java
    TyAiUsageService.java
    TyAiKnowledgeService.java           # 第二阶段
    provider
      AiProvider.java
      AiChatRequest.java
      AiChatResponse.java
      AiStreamListener.java
    impl
      TyAiChatServiceImpl.java
      TyAiQuotaServiceImpl.java
      TyAiUsageServiceImpl.java
      TyAiKnowledgeServiceImpl.java     # 第二阶段
      OpenAiProvider.java
      MockAiProvider.java
  repository/ai
  repository/ai/impl
  mapper/ai
  entity/ai
  dto/ai
  config/ai
    AiProperties.java
```

## 5. 数据库设计

### 5.1 会话表：ty_ai_chat_session

保存用户的对话会话。

| 字段 | 类型 | 说明 |
| --- | --- | --- |
| id | bigint | 主键 |
| user_id | bigint | 会话所属用户 |
| campus_id | bigint | 所属校区，可选 |
| title | varchar(100) | 会话标题 |
| chat_type | varchar(32) | `general` 普通问答，`knowledge` 知识库问答，`business` 业务问答 |
| model | varchar(64) | 使用模型 |
| system_prompt | text | 会话级系统提示词，可选 |
| summary | text | 超长上下文摘要 |
| status | tinyint | 1 正常，0 删除/归档 |
| last_message_at | datetime | 最后消息时间 |
| created_at | datetime | 创建时间 |
| updated_at | datetime | 更新时间 |
| is_deleted | tinyint | 逻辑删除 |

建议索引：

- `idx_user_last_message(user_id, last_message_at)`
- `idx_campus_user(campus_id, user_id)`

### 5.2 消息表：ty_ai_chat_message

保存用户消息和助手回复。

| 字段 | 类型 | 说明 |
| --- | --- | --- |
| id | bigint | 主键 |
| session_id | bigint | 会话 ID |
| user_id | bigint | 消息所属用户，冗余用于权限和查询 |
| parent_message_id | bigint | 上一条消息 ID，可选，用于分支或重新生成 |
| role | varchar(16) | `user`、`assistant`、`system` |
| content | longtext | 消息正文 |
| content_type | varchar(32) | `text`、`markdown`、`json` |
| status | varchar(32) | `success`、`generating`、`failed`、`stopped` |
| prompt_tokens | int | 输入 token |
| completion_tokens | int | 输出 token |
| total_tokens | int | 总 token |
| finish_reason | varchar(64) | 结束原因 |
| error_message | varchar(500) | 失败原因 |
| created_at | datetime | 创建时间 |
| updated_at | datetime | 更新时间 |
| is_deleted | tinyint | 逻辑删除 |

建议索引：

- `idx_session_created(session_id, created_at)`
- `idx_user_created(user_id, created_at)`

### 5.3 调用日志表：ty_ai_chat_request_log

保存每次模型调用记录，用于排障和审计。

| 字段 | 类型 | 说明 |
| --- | --- | --- |
| id | bigint | 主键 |
| request_id | varchar(64) | 请求唯一 ID |
| user_id | bigint | 用户 ID |
| session_id | bigint | 会话 ID |
| message_id | bigint | 用户消息 ID |
| provider | varchar(32) | 模型供应商 |
| model | varchar(64) | 模型名称 |
| request_status | varchar(32) | `success`、`failed`、`timeout`、`stopped` |
| prompt_tokens | int | 输入 token |
| completion_tokens | int | 输出 token |
| total_tokens | int | 总 token |
| latency_ms | int | 调用耗时 |
| error_code | varchar(64) | 错误码 |
| error_message | varchar(500) | 错误信息 |
| created_at | datetime | 创建时间 |

### 5.4 用户额度表：ty_ai_user_quota

用于按用户控制问答次数和 token 额度。

| 字段 | 类型 | 说明 |
| --- | --- | --- |
| id | bigint | 主键 |
| user_id | bigint | 用户 ID |
| quota_period | varchar(16) | `daily`、`monthly`、`total` |
| question_limit | int | 周期内最大提问次数 |
| token_limit | int | 周期内最大 token 数 |
| used_questions | int | 已使用提问次数 |
| used_tokens | int | 已使用 token 数 |
| period_start | date | 周期开始日期 |
| period_end | date | 周期结束日期 |
| status | tinyint | 1 启用，0 禁用 |
| created_at | datetime | 创建时间 |
| updated_at | datetime | 更新时间 |

建议索引：

- `uk_user_period(user_id, quota_period, period_start, period_end)`

### 5.5 用量流水表：ty_ai_usage_record

建议保留流水，不只更新额度表。这样后续可以做统计、回滚和争议排查。

| 字段 | 类型 | 说明 |
| --- | --- | --- |
| id | bigint | 主键 |
| user_id | bigint | 用户 ID |
| session_id | bigint | 会话 ID |
| message_id | bigint | 助手消息 ID |
| request_id | varchar(64) | 请求 ID |
| quota_period | varchar(16) | 计入哪个周期 |
| question_count | int | 通常为 1 |
| prompt_tokens | int | 输入 token |
| completion_tokens | int | 输出 token |
| total_tokens | int | 总 token |
| created_at | datetime | 创建时间 |

### 5.6 知识库表：ty_ai_knowledge_base

第二阶段使用。

| 字段 | 类型 | 说明 |
| --- | --- | --- |
| id | bigint | 主键 |
| name | varchar(100) | 知识库名称 |
| description | varchar(500) | 描述 |
| scope_type | varchar(32) | `global`、`campus`、`role`、`user` |
| campus_id | bigint | 校区范围，可选 |
| owner_user_id | bigint | 创建人 |
| status | tinyint | 1 启用，0 禁用 |
| created_at | datetime | 创建时间 |
| updated_at | datetime | 更新时间 |
| is_deleted | tinyint | 逻辑删除 |

### 5.7 知识库文档表：ty_ai_knowledge_document

第二阶段使用。

| 字段 | 类型 | 说明 |
| --- | --- | --- |
| id | bigint | 主键 |
| knowledge_base_id | bigint | 知识库 ID |
| title | varchar(200) | 文档标题 |
| file_name | varchar(255) | 文件名 |
| file_url | varchar(500) | 文件地址 |
| file_type | varchar(32) | pdf、docx、txt、md 等 |
| parse_status | varchar(32) | `pending`、`success`、`failed` |
| error_message | varchar(500) | 解析失败原因 |
| created_by | bigint | 上传人 |
| created_at | datetime | 创建时间 |
| updated_at | datetime | 更新时间 |
| is_deleted | tinyint | 逻辑删除 |

### 5.8 文档片段表：ty_ai_document_chunk

第二阶段使用。

| 字段 | 类型 | 说明 |
| --- | --- | --- |
| id | bigint | 主键 |
| knowledge_base_id | bigint | 知识库 ID |
| document_id | bigint | 文档 ID |
| chunk_index | int | 片段序号 |
| content | text | 片段内容 |
| content_hash | varchar(64) | 内容 hash，用于去重 |
| embedding_id | varchar(128) | 向量索引 ID，可选 |
| metadata_json | text | 页码、章节、来源等元数据 |
| created_at | datetime | 创建时间 |

说明：如果后续使用支持向量字段的数据库，可以在该表增加 `embedding` 字段；如果使用外部向量库，则保存 `embedding_id`。

## 6. 额度控制设计

可以做到按用户控制问答次数和 token 额度分配，建议同时支持以下维度：

- 每日提问次数，例如每天 30 次。
- 每月提问次数，例如每月 500 次。
- 每日 token 额度，例如每天 100000 tokens。
- 每月 token 额度，例如每月 2000000 tokens。
- 用户级单独配置，例如管理员、老师、财务有不同额度。
- 后续可扩展角色级默认额度、校区级总额度。

### 6.1 调用前校验

用户发送消息后，后端先执行：

1. 校验 JWT，获取当前用户。
2. 校验会话是否属于当前用户。
3. 校验消息长度和敏感输入。
4. 查询用户当前周期额度。
5. 如果 `used_questions >= question_limit`，拒绝请求。
6. 如果 `used_tokens >= token_limit`，拒绝请求。
7. 预估本次输入 token，如果预估后明显超额，拒绝请求。

### 6.2 调用后扣减

模型返回后，按实际 token 回写：

1. 写入助手消息。
2. 写入请求日志。
3. 写入用量流水。
4. 更新 `ty_ai_user_quota.used_questions` 和 `used_tokens`。

### 6.3 并发控制

额度扣减要防止并发超用，建议：

- 使用数据库条件更新，例如 `used_tokens + 本次消耗 <= token_limit`。
- 或者对用户额度行加乐观锁字段 `version`。
- 对高并发场景可引入 Redis 原子计数，数据库异步落账。

第一版可以先用数据库条件更新，简单且够用。

## 7. 接口设计

### 7.1 会话接口

```text
POST   /api/ai/chat/session/create
POST   /api/ai/chat/session/page
GET    /api/ai/chat/session/detail/{id}
DELETE /api/ai/chat/session/{id}
POST   /api/ai/chat/session/update-title
```

### 7.2 消息接口

```text
GET  /api/ai/chat/message/list/{sessionId}
POST /api/ai/chat/message/send
POST /api/ai/chat/message/regenerate
POST /api/ai/chat/message/stop
```

### 7.3 流式接口

推荐第一版采用 SSE。

```text
GET /api/ai/chat/message/stream?requestId=xxx
```

SSE 事件建议：

```text
event: start
data: {"requestId":"...","messageId":123}

event: delta
data: {"content":"部分文本"}

event: usage
data: {"promptTokens":100,"completionTokens":200,"totalTokens":300}

event: done
data: {"finishReason":"stop"}

event: error
data: {"message":"错误信息"}
```

### 7.4 额度接口

```text
GET  /api/ai/quota/me
POST /api/ai/quota/user/page
POST /api/ai/quota/user/update
POST /api/ai/quota/user/reset
```

其中 `/api/ai/quota/me` 给普通用户查看自己的剩余次数和 token，其他接口给管理员使用。

### 7.5 知识库接口

第二阶段使用。

```text
POST   /api/ai/knowledge/base/create
POST   /api/ai/knowledge/base/page
POST   /api/ai/knowledge/document/upload
POST   /api/ai/knowledge/document/parse
POST   /api/ai/knowledge/document/delete
POST   /api/ai/knowledge/search
```

## 8. 连续对话上下文策略

不要无限发送所有历史消息，建议：

1. 最近 10 到 20 轮消息直接进入上下文。
2. 更早的历史消息生成摘要，保存到 `ty_ai_chat_session.summary`。
3. 每次调用模型时拼接：

```text
系统提示词
会话摘要
知识库检索结果，知识库模式才有
最近 N 轮对话
当前用户问题
```

当会话消息过长或 token 超过阈值时，异步触发摘要更新。

## 9. 模型 Provider 设计

建议定义统一接口，业务服务不直接调用具体模型 SDK。

```java
public interface AiProvider {
    AiChatResponse chat(AiChatRequest request);

    void streamChat(AiChatRequest request, AiStreamListener listener);
}
```

`AiChatRequest` 建议包含：

- `requestId`
- `userId`
- `sessionId`
- `model`
- `messages`
- `temperature`
- `maxTokens`
- `metadata`

`AiStreamListener` 建议包含：

- `onStart`
- `onDelta`
- `onUsage`
- `onComplete`
- `onError`

这样后续可以很容易扩展：

- `OpenAiProvider`
- `MockAiProvider`
- `LocalModelProvider`
- `KnowledgeEnhancedProvider`

## 10. 配置设计

`application.yml` 中建议只放非敏感默认值，密钥使用环境变量。

```yaml
ty:
  ai:
    enabled: true
    provider: openai
    model: ${TY_AI_MODEL:gpt-5.1}
    api-key: ${TY_AI_API_KEY:}
    base-url: ${TY_AI_BASE_URL:https://api.openai.com/v1}
    timeout-seconds: 120
    stream-timeout-seconds: 180
    quota:
      default-daily-question-limit: 30
      default-daily-token-limit: 100000
      default-monthly-question-limit: 500
      default-monthly-token-limit: 2000000
```

注意：

- `TY_AI_API_KEY` 不能提交到 Git。
- 如果公司有代理网关，可以把 `TY_AI_BASE_URL` 指向公司网关。
- 具体模型名建议作为配置项，不要写死在代码中。

## 11. 前端设计

前端项目：`/Users/pzy/Documents/WebstormProjects/ty-cloud-web`。

建议新增：

```text
src/views/AiChatView.vue
src/api/aiChat.js
src/api/aiQuota.js
src/router/routes.js             # 增加智能问答路由
src/layouts/AppLayout.vue         # 增加菜单入口
```

页面结构建议：

```text
智能问答页面
  左侧：会话列表、搜索、新建会话
  中间：消息流、Markdown 渲染、代码块复制
  底部：输入框、发送按钮、停止按钮、模型选择
  右侧可选：额度信息、知识库选择、引用来源
```

第一版交互：

- 新建会话。
- 点击会话加载历史消息。
- 输入问题并发送。
- 使用 SSE 接收流式回复。
- 生成中允许停止。
- 显示剩余次数和剩余 token。
- 超额时给出明确提示。

ChatGPT 风格关键点：

- 消息区保持整洁，用户消息右侧，助手消息左侧或统一纵向排布。
- 回复支持 Markdown 和代码高亮。
- 生成过程逐步追加文本。
- 输入框固定底部，多行文本，`Enter` 发送，`Shift + Enter` 换行。
- 不在页面堆解释性文案，主要呈现可用的聊天工作台。

## 12. 权限设计

### 12.1 普通用户

- 只能查看和操作自己的会话。
- 只能使用管理员分配的额度。
- 只能访问授权知识库。

### 12.2 管理员

- 可配置用户额度。
- 可查看用量统计。
- 可管理知识库。
- 可查看异常调用日志。

### 12.3 数据隔离

所有查询必须带上当前登录用户或授权范围判断：

- 会话：`session.user_id == currentUser.id`
- 消息：`message.user_id == currentUser.id`
- 知识库：根据 `scope_type`、`campus_id`、角色、用户授权判断

## 13. 企业知识库权限模型

知识库可以按范围授权：

- `global`：全系统可用。
- `campus`：指定校区可用。
- `role`：指定角色可用。
- `user`：指定用户可用。

如果后续要精细控制，可增加授权表：

```text
ty_ai_knowledge_acl
  id
  knowledge_base_id
  subject_type    # user, role, campus
  subject_id
  permission      # read, manage
  created_at
```

问答时检索范围必须先经过 ACL 过滤，不能先查全部文档再让模型判断权限。

## 14. 审计与风控

建议记录：

- 用户 ID
- 请求 IP
- 会话 ID
- 模型名称
- token 使用量
- 请求耗时
- 错误信息
- 是否命中知识库
- 引用的知识库文档 ID

风控建议：

- 单条消息长度限制。
- 单用户并发生成限制。
- 每分钟请求频率限制。
- 敏感词或敏感操作提示。
- 模型返回失败时不扣或少扣额度，具体策略需要产品确认。

## 15. 推荐实施顺序

### 第一阶段：普通智能问答

1. 新增 AI 模块包结构。
2. 新增会话表、消息表、请求日志表、额度表、用量流水表。
3. 实现 `AiProvider` 和 `MockAiProvider`。
4. 实现用户额度查询和扣减。
5. 实现非流式发送消息接口。
6. 实现 SSE 流式输出。
7. 前端新增 `AiChatView.vue`。
8. 增加菜单和路由。
9. 增加基础测试：额度不足、会话隔离、消息保存、模型异常。

### 第二阶段：企业知识库问答

1. 新增知识库、文档、文档片段表。
2. 实现文档上传和解析。
3. 实现 chunk 切分和 embedding 入库。
4. 实现知识库检索。
5. 问答时注入检索结果。
6. 前端展示引用来源。
7. 增加知识库权限控制。

### 第三阶段：业务数据问答

1. 定义可调用业务工具，例如查询学员、课程、合同、考勤。
2. 为工具增加权限和参数校验。
3. 模型根据用户问题选择工具。
4. 后端执行工具并把结果交给模型总结。

## 16. 第一版验收标准

- 用户登录后可以进入智能问答页面。
- 用户可以创建会话并连续对话。
- 刷新页面后历史消息仍然存在。
- 回复支持流式展示。
- 额度不足时禁止继续提问并显示剩余额度。
- 用户不能访问其他用户的会话和消息。
- 模型调用失败时有错误提示，并记录日志。
- 管理员可以为用户调整问答次数和 token 额度。

## 17. 风险与注意事项

- 模型输出不等于系统真实数据，涉及学员、财务、合同等业务数据时必须通过受控工具查询。
- 企业知识库回答必须展示来源，降低误答风险。
- token 额度建议以模型返回的实际 usage 为准，预估 token 只用于调用前拦截。
- 流式输出期间如果用户停止生成，需要把助手消息标记为 `stopped`。
- 生产环境必须配置真实密钥和合理超时，不能使用默认空密钥。
- 不建议前端直连模型 API，否则密钥泄露且无法统一审计和控额。

