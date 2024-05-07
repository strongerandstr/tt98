CREATE TABLE `comment` (
    `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `article_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '文章ID',
    `user_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '用户id',
    `content` varchar(300) NOT NULL DEFAULT '' COMMENT '评论内容',
    `top_comment_id` int NOT NULL DEFAULT '0' COMMENT '顶级评论ID',
    `parent_comment_id` int unsigned NOT NULL DEFAULT '0' COMMENT '父评论ID',
    `deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_article_id` (`article_id`),
    KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='评论表';