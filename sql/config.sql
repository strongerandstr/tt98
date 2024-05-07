CREATE TABLE `config` (
    `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `type` tinyint NOT NULL DEFAULT '0' COMMENT '配置类型：1-首页，2-侧边栏，3-广告位，4-公告',
    `name` varchar(64) NOT NULL DEFAULT '' COMMENT '名称',
    `banner_url` varchar(256) NOT NULL DEFAULT '' COMMENT '图片链接',
    `jump_url` varchar(256) NOT NULL DEFAULT '' COMMENT '跳转链接',
    `content` varchar(256) NOT NULL DEFAULT '' COMMENT '内容',
    `rank` tinyint NOT NULL DEFAULT '0' COMMENT '排序',
    `status` tinyint NOT NULL DEFAULT '0' COMMENT '状态：0-未发布，1-已发布',
    `tags` varchar(64) NOT NULL DEFAULT '' COMMENT '配置关联标签，英文逗号分隔 1 火 2 官方 3 推荐',
    `extra` varchar(1024) NOT NULL DEFAULT '{}' COMMENT '扩展信息',
    `deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='配置表';