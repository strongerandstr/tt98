CREATE TABLE `user_ai_history` (
    `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '用户id',
    `ai_type` tinyint NOT NULL DEFAULT '0' COMMENT '使用的AI类型 0=技术派 1=chatgpt3.5  2=chatgpt4 3=讯飞',
    `question` varchar(512) NOT NULL DEFAULT '' COMMENT '问题',
    `answer` text COMMENT '回答',
    `deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_user_id_ai_type` (`user_id`,`ai_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户与AI的聊天历史';