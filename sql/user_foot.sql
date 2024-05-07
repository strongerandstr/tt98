CREATE TABLE `user_foot` (
    `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '用户id',
    `document_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '文档ID（文章/评论）',
    `document_type` tinyint NOT NULL DEFAULT '1' COMMENT '文档类型：1-文章，2-评论',
    `document_user_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '发布该文档的用户ID',
    `collection_stat` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '收藏状态: 0-未收藏，1-已收藏，2-取消收藏',
    `read_stat` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '阅读状态: 0-未读，1-已读',
    `comment_stat` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '评论状态: 0-未评论，1-已评论，2-删除评论',
    `praise_stat` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '点赞状态: 0-未点赞，1-已点赞，2-取消点赞',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_user_doucument` (`user_id`,`document_id`,`document_type`),
    KEY `idx_doucument_id` (`document_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户足迹表';