CREATE TABLE `tag` (
    `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `tag_name` varchar(120) NOT NULL COMMENT '标签名称',
    `tag_type` tinyint NOT NULL DEFAULT '1' COMMENT '标签类型：1-系统标签，2-自定义标签',
    `category_id` int unsigned NOT NULL DEFAULT '0' COMMENT '类目ID',
    `status` tinyint NOT NULL DEFAULT '0' COMMENT '状态：0-未发布，1-已发布',
    `deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_category_id` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=137 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='标签管理表';