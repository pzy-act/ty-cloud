SET NAMES utf8mb4;

USE `ty_cloud`;

CREATE TABLE IF NOT EXISTS `ty_ai_chat_session` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id` BIGINT NOT NULL COMMENT '会话所属用户ID',
    `campus_id` BIGINT DEFAULT NULL COMMENT '所属校区ID',
    `title` VARCHAR(100) NOT NULL COMMENT '会话标题',
    `chat_type` VARCHAR(32) NOT NULL DEFAULT 'general' COMMENT '会话类型：general/knowledge/business',
    `model` VARCHAR(64) DEFAULT NULL COMMENT '使用模型',
    `system_prompt` TEXT DEFAULT NULL COMMENT '会话级系统提示词',
    `summary` TEXT DEFAULT NULL COMMENT '超长上下文摘要',
    `status` TINYINT NOT NULL DEFAULT 1 COMMENT '状态：1正常，0归档',
    `last_message_time` DATETIME DEFAULT NULL COMMENT '最后消息时间',
    `is_deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '删除标记',
    `created_by` BIGINT DEFAULT 0 COMMENT '创建人ID',
    `created_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_by` BIGINT DEFAULT 0 COMMENT '更新人ID',
    `updated_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_user_last_message` (`user_id`, `last_message_time`),
    KEY `idx_campus_user` (`campus_id`, `user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='AI会话表';

CREATE TABLE IF NOT EXISTS `ty_ai_chat_message` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `session_id` BIGINT NOT NULL COMMENT '会话ID',
    `user_id` BIGINT NOT NULL COMMENT '消息所属用户ID',
    `parent_message_id` BIGINT DEFAULT NULL COMMENT '上一条消息ID',
    `role` VARCHAR(16) NOT NULL COMMENT '消息角色：user/assistant/system',
    `content` LONGTEXT NOT NULL COMMENT '消息正文',
    `content_type` VARCHAR(32) NOT NULL DEFAULT 'markdown' COMMENT '内容类型',
    `status` VARCHAR(32) NOT NULL DEFAULT 'success' COMMENT '状态：success/generating/failed/stopped',
    `prompt_tokens` INT NOT NULL DEFAULT 0 COMMENT '输入token数',
    `completion_tokens` INT NOT NULL DEFAULT 0 COMMENT '输出token数',
    `total_tokens` INT NOT NULL DEFAULT 0 COMMENT '总token数',
    `finish_reason` VARCHAR(64) DEFAULT NULL COMMENT '结束原因',
    `error_message` VARCHAR(500) DEFAULT NULL COMMENT '错误信息',
    `is_deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '删除标记',
    `created_by` BIGINT DEFAULT 0 COMMENT '创建人ID',
    `created_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_by` BIGINT DEFAULT 0 COMMENT '更新人ID',
    `updated_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_session_created` (`session_id`, `created_time`),
    KEY `idx_user_created` (`user_id`, `created_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='AI聊天消息表';

CREATE TABLE IF NOT EXISTS `ty_ai_chat_request_log` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `request_id` VARCHAR(64) NOT NULL COMMENT '请求唯一ID',
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `session_id` BIGINT DEFAULT NULL COMMENT '会话ID',
    `message_id` BIGINT DEFAULT NULL COMMENT '用户消息ID',
    `provider` VARCHAR(32) NOT NULL COMMENT '模型供应商',
    `model` VARCHAR(64) DEFAULT NULL COMMENT '模型名称',
    `request_status` VARCHAR(32) NOT NULL COMMENT '请求状态',
    `prompt_tokens` INT NOT NULL DEFAULT 0 COMMENT '输入token数',
    `completion_tokens` INT NOT NULL DEFAULT 0 COMMENT '输出token数',
    `total_tokens` INT NOT NULL DEFAULT 0 COMMENT '总token数',
    `latency_ms` INT NOT NULL DEFAULT 0 COMMENT '调用耗时毫秒',
    `error_code` VARCHAR(64) DEFAULT NULL COMMENT '错误码',
    `error_message` VARCHAR(500) DEFAULT NULL COMMENT '错误信息',
    `created_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_request_id` (`request_id`),
    KEY `idx_user_created` (`user_id`, `created_time`),
    KEY `idx_session_created` (`session_id`, `created_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='AI模型调用日志表';

CREATE TABLE IF NOT EXISTS `ty_ai_user_quota` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `quota_period` VARCHAR(16) NOT NULL COMMENT '额度周期：daily/monthly/total',
    `question_limit` INT NOT NULL DEFAULT 0 COMMENT '周期内最大提问次数，0表示不限制',
    `token_limit` INT NOT NULL DEFAULT 0 COMMENT '周期内最大token数，0表示不限制',
    `used_questions` INT NOT NULL DEFAULT 0 COMMENT '已使用提问次数',
    `used_tokens` INT NOT NULL DEFAULT 0 COMMENT '已使用token数',
    `period_start` DATE NOT NULL COMMENT '周期开始日期',
    `period_end` DATE NOT NULL COMMENT '周期结束日期',
    `status` TINYINT NOT NULL DEFAULT 1 COMMENT '状态：1启用，0禁用',
    `is_deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '删除标记',
    `created_by` BIGINT DEFAULT 0 COMMENT '创建人ID',
    `created_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_by` BIGINT DEFAULT 0 COMMENT '更新人ID',
    `updated_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_user_period` (`user_id`, `quota_period`, `period_start`, `period_end`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='AI用户额度表';

CREATE TABLE IF NOT EXISTS `ty_ai_usage_record` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `session_id` BIGINT DEFAULT NULL COMMENT '会话ID',
    `message_id` BIGINT DEFAULT NULL COMMENT '助手消息ID',
    `request_id` VARCHAR(64) NOT NULL COMMENT '请求ID',
    `quota_period` VARCHAR(16) NOT NULL COMMENT '计入周期',
    `question_count` INT NOT NULL DEFAULT 1 COMMENT '提问次数',
    `prompt_tokens` INT NOT NULL DEFAULT 0 COMMENT '输入token数',
    `completion_tokens` INT NOT NULL DEFAULT 0 COMMENT '输出token数',
    `total_tokens` INT NOT NULL DEFAULT 0 COMMENT '总token数',
    `created_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    KEY `idx_user_created` (`user_id`, `created_time`),
    KEY `idx_request_id` (`request_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='AI用量流水表';

CREATE TABLE IF NOT EXISTS `ty_ai_knowledge_base` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `name` VARCHAR(100) NOT NULL COMMENT '知识库名称',
    `description` VARCHAR(500) DEFAULT NULL COMMENT '描述',
    `scope_type` VARCHAR(32) NOT NULL DEFAULT 'global' COMMENT '范围：global/campus/role/user',
    `campus_id` BIGINT DEFAULT NULL COMMENT '校区ID',
    `owner_user_id` BIGINT DEFAULT NULL COMMENT '创建人用户ID',
    `status` TINYINT NOT NULL DEFAULT 1 COMMENT '状态：1启用，0禁用',
    `is_deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '删除标记',
    `created_by` BIGINT DEFAULT 0 COMMENT '创建人ID',
    `created_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_by` BIGINT DEFAULT 0 COMMENT '更新人ID',
    `updated_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_scope` (`scope_type`, `campus_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='AI知识库表';

CREATE TABLE IF NOT EXISTS `ty_ai_knowledge_document` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `knowledge_base_id` BIGINT NOT NULL COMMENT '知识库ID',
    `title` VARCHAR(200) NOT NULL COMMENT '文档标题',
    `file_name` VARCHAR(255) DEFAULT NULL COMMENT '文件名',
    `file_url` VARCHAR(500) DEFAULT NULL COMMENT '文件地址',
    `file_type` VARCHAR(32) DEFAULT NULL COMMENT '文件类型',
    `parse_status` VARCHAR(32) NOT NULL DEFAULT 'pending' COMMENT '解析状态',
    `error_message` VARCHAR(500) DEFAULT NULL COMMENT '解析失败原因',
    `is_deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '删除标记',
    `created_by` BIGINT DEFAULT 0 COMMENT '创建人ID',
    `created_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_by` BIGINT DEFAULT 0 COMMENT '更新人ID',
    `updated_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_base_created` (`knowledge_base_id`, `created_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='AI知识库文档表';

CREATE TABLE IF NOT EXISTS `ty_ai_document_chunk` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `knowledge_base_id` BIGINT NOT NULL COMMENT '知识库ID',
    `document_id` BIGINT NOT NULL COMMENT '文档ID',
    `chunk_index` INT NOT NULL COMMENT '片段序号',
    `content` TEXT NOT NULL COMMENT '片段内容',
    `content_hash` VARCHAR(64) DEFAULT NULL COMMENT '内容hash',
    `embedding_id` VARCHAR(128) DEFAULT NULL COMMENT '向量索引ID',
    `metadata_json` TEXT DEFAULT NULL COMMENT '元数据JSON',
    `created_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    KEY `idx_base_document` (`knowledge_base_id`, `document_id`),
    KEY `idx_embedding_id` (`embedding_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='AI文档片段表';

SET @campus_id = COALESCE(
    (SELECT `campus_id` FROM `ty_sys_user` WHERE `username` = 'admin' AND `is_deleted` = 0 LIMIT 1),
    (SELECT `id` FROM `ty_sys_campus` WHERE `is_deleted` = 0 ORDER BY `id` LIMIT 1),
    1
);

SET @admin_role_id = (
    SELECT `id`
    FROM `ty_sys_role`
    WHERE `role_code` = 'ADMIN'
      AND `is_deleted` = 0
    LIMIT 1
);

SET @ai_parent_menu_id = (
    SELECT `id`
    FROM `ty_sys_menu`
    WHERE `route_path` = '/ai'
      AND `menu_type` = 1
      AND `is_deleted` = 0
    LIMIT 1
);

INSERT INTO `ty_sys_menu` (
    `campus_id`, `parent_id`, `menu_name`, `menu_type`, `route_path`, `component`,
    `perms`, `icon`, `sort_no`, `visible`, `status`, `is_deleted`, `created_by`, `updated_by`
)
SELECT @campus_id, 0, '智能问答', 1, '/ai', 'Layout', NULL, 'chat-dot-round', 130, 1, 1, 0, 0, 0
WHERE @ai_parent_menu_id IS NULL;

SET @ai_parent_menu_id = (
    SELECT `id`
    FROM `ty_sys_menu`
    WHERE `route_path` = '/ai'
      AND `menu_type` = 1
      AND `is_deleted` = 0
    LIMIT 1
);

INSERT INTO `ty_sys_menu` (
    `campus_id`, `parent_id`, `menu_name`, `menu_type`, `route_path`, `component`,
    `perms`, `icon`, `sort_no`, `visible`, `status`, `is_deleted`, `created_by`, `updated_by`
)
SELECT @campus_id, @ai_parent_menu_id, '智能问答', 2, '/ai/chat', 'ai/chat/index', 'ai:chat:use', NULL, 10, 1, 1, 0, 0, 0
WHERE @ai_parent_menu_id IS NOT NULL
  AND NOT EXISTS (
      SELECT 1 FROM `ty_sys_menu`
      WHERE `route_path` = '/ai/chat'
        AND `menu_type` = 2
        AND `is_deleted` = 0
  );

INSERT INTO `ty_sys_menu` (
    `campus_id`, `parent_id`, `menu_name`, `menu_type`, `route_path`, `component`,
    `perms`, `icon`, `sort_no`, `visible`, `status`, `is_deleted`, `created_by`, `updated_by`
)
SELECT @campus_id, @ai_parent_menu_id, '额度管理', 2, '/ai/quota', 'ai/quota/index', 'ai:quota:manage', NULL, 20, 1, 1, 0, 0, 0
WHERE @ai_parent_menu_id IS NOT NULL
  AND NOT EXISTS (
      SELECT 1 FROM `ty_sys_menu`
      WHERE `route_path` = '/ai/quota'
        AND `menu_type` = 2
        AND `is_deleted` = 0
  );

INSERT INTO `ty_sys_role_menu` (`campus_id`, `role_id`, `menu_id`, `is_deleted`, `created_by`, `updated_by`)
SELECT @campus_id, @admin_role_id, m.`id`, 0, 0, 0
FROM `ty_sys_menu` m
WHERE @admin_role_id IS NOT NULL
  AND m.`route_path` IN ('/ai', '/ai/chat', '/ai/quota')
  AND m.`is_deleted` = 0
  AND NOT EXISTS (
      SELECT 1
      FROM `ty_sys_role_menu` rm
      WHERE rm.`role_id` = @admin_role_id
        AND rm.`menu_id` = m.`id`
        AND rm.`is_deleted` = 0
  );
