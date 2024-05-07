CREATE TABLE `notify_msg` (
    `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `related_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '关联的主键',
    `notify_user_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '通知的用户id',
    `operate_user_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '触发这个通知的用户id',
    `msg` varchar(1024) NOT NULL DEFAULT '' COMMENT '消息内容',
    `type` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '类型: 0-默认，1-评论，2-回复 3-点赞 4-收藏 5-关注 6-系统',
    `state` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '阅读状态: 0-未读，1-已读',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`id`),
    KEY `key_notify_user_id_type_state` (`notify_user_id`,`type`,`state`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='消息通知列表';