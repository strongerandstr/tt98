CREATE TABLE `user_info` (
    `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '用户id',
    `user_name` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名',
    `photo` varchar(128) NOT NULL DEFAULT '' COMMENT '用户图像',
    `position` varchar(50) NOT NULL DEFAULT '' COMMENT '职位',
    `company` varchar(50) NOT NULL DEFAULT '' COMMENT '公司',
    `profile` varchar(225) NOT NULL DEFAULT '' COMMENT '个人简介',
    `user_role` int NOT NULL DEFAULT '0' COMMENT '0 普通用户 1 超管',
    `extend` varchar(1024) NOT NULL DEFAULT '' COMMENT '扩展字段',
    `ip` json NOT NULL COMMENT '用户的ip信息',
    `deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`id`),
    KEY `key_user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户个人信息表';