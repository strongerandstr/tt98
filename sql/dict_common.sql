CREATE TABLE `dict_common` (
    `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `type_code` varchar(100) NOT NULL DEFAULT '' COMMENT '字典类型，sex, status 等',
    `dict_code` varchar(100) NOT NULL DEFAULT '' COMMENT '字典类型的值编码',
    `dict_desc` varchar(200) NOT NULL DEFAULT '' COMMENT '字典类型的值描述',
    `sort_no` int unsigned NOT NULL DEFAULT '0' COMMENT '排序编号',
    `remark` varchar(500) DEFAULT '' COMMENT '备注',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_type_code_dict_code` (`type_code`,`dict_code`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='通用数据字典';