CREATE TABLE `user` (
    `id` int(10) unsigned not null auto_increment comment '主键ID',
    `third_account_id` varchar(128) not null default '' comment '第三方用户ID',
    `user_name` varchar(64) not null default '' comment '用户名',
    `password` varchar(128) not null default '' comment '密码',
    `login_type` tinyint(4) not null default '0' comment '登录方式:0-微信登录,1-账号登录',
    `deleted` tinyint(4) not null default '0' comment '是否删除',
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '更新时间',
    PRIMARY KEY (`id`),
    KEY `key_third_account_id` (`third_account_id`),
    KEY `key_user_name` (`user_name`)
) ENGINE = InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='用户登录表';