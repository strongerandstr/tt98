CREATE TABLE `banner` (
    `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `banner_name` varchar(64) NOT NULL DEFAULT '' COMMENT '名称',
    `banner_url` varchar(256) NOT NULL DEFAULT '' COMMENT '图片url',
    `banner_type` tinyint NOT NULL DEFAULT '0' COMMENT '类型：1-首页，2-侧边栏，3-广告位',
    `rank` tinyint NOT NULL DEFAULT '0' COMMENT '排序',
    `status` tinyint NOT NULL DEFAULT '0' COMMENT '状态：0-未发布，1-已发布',
    `deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='banner表';