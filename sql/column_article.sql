CREATE TABLE `column_article` (
    `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `column_id` int unsigned NOT NULL DEFAULT '0' COMMENT '专栏ID',
    `article_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '文章ID',
    `section` int unsigned NOT NULL DEFAULT '0' COMMENT '章节顺序，越小越靠前',
    `read_type` tinyint DEFAULT '0' COMMENT '文章阅读类型 0-沿用专栏规则 1-登录阅读 2-限时免费 3-星球',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`id`),
    KEY `column_id` (`column_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='专栏文章列表';