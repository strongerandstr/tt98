CREATE TABLE `column_info` (
    `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `column_name` varchar(64) NOT NULL DEFAULT '' COMMENT '专栏名',
    `user_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '用户id',
    `introduction` varchar(256) NOT NULL DEFAULT '' COMMENT '专栏简述',
    `cover` varchar(128) NOT NULL DEFAULT '' COMMENT '专栏封面',
    `state` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '状态: 0-审核中，1-连载，2-完结',
    `publish_time` timestamp NOT NULL DEFAULT '1970-01-02 00:00:00' COMMENT '上线时间',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    `section` int unsigned NOT NULL DEFAULT '0' COMMENT '排序',
    `nums` int unsigned NOT NULL DEFAULT '0' COMMENT '专栏预计的更新的文章数',
    `type` int unsigned NOT NULL DEFAULT '0' COMMENT '专栏类型 0-免费 1-登录阅读 2-限时免费 3-星球',
    `free_start_time` timestamp NOT NULL DEFAULT '1970-01-02 00:00:00' COMMENT '限时免费开始时间',
    `free_end_time` timestamp NOT NULL DEFAULT '1970-01-02 00:00:00' COMMENT '限时免费结束时间',
    PRIMARY KEY (`id`),
    KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='专栏';