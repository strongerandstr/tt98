CREATE TABLE `read_count` (
    `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `document_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '文档ID（文章/评论）',
    `document_type` tinyint NOT NULL DEFAULT '1' COMMENT '文档类型：1-文章，2-评论',
    `cnt` int unsigned NOT NULL DEFAULT '0' COMMENT '访问计数',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_document_id_type` (`document_id`,`document_type`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='计数表';