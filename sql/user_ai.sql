CREATE TABLE `user_ai` (
   `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
   `user_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '用户id',
   `star_number` varchar(64) NOT NULL DEFAULT '0' COMMENT '知识星球编号',
   `star_type` tinyint NOT NULL DEFAULT '0' COMMENT '星球来源 1=java进阶之路 2=技术派',
   `inviter_user_id` bigint NOT NULL DEFAULT '0' COMMENT '当前用户绑定的邀请者',
   `invite_code` varchar(8) NOT NULL DEFAULT '' COMMENT '邀请码',
   `invite_num` int NOT NULL DEFAULT '0' COMMENT '当前用户邀请的人数',
   `state` int NOT NULL DEFAULT '0' COMMENT '0 审核中 1 试用中 2 审核通过 3 审核拒绝',
   `strategy` int NOT NULL DEFAULT '0' COMMENT '二进制表示法：0位绑定微信公众号 1位 绑定邀请 2位绑定java进阶星球 3位绑定 技术派星球',
   `deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除',
   `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
   PRIMARY KEY (`id`),
   KEY `idx_user_id` (`user_id`),
   KEY `idx_inviter_user_id` (`inviter_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='AI聊天运营策略信息表';