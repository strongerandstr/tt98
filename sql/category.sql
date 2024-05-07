CREATE TABLE `category` (
    `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `category_name` varchar(64) NOT NULL DEFAULT '' COMMENT '类目名称',
    `status` tinyint NOT NULL DEFAULT '0' COMMENT '状态：0-未发布，1-已发布',
    `rank` tinyint NOT NULL DEFAULT '0' COMMENT '排序',
    `deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='类目管理表';