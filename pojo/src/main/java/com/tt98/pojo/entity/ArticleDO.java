package com.tt98.pojo.entity;

public class ArticleDO {

    private long userId;

    private int articleType;



//        `id` int(10) unsigned not null auto_increment comment '主键ID',
//            `user_id` int(10) unsigned not null default '0' comment '用户ID',
//            `article_type` tinyint(4) not null default '1' comment '文章类型:1-博文,2-问答',
//            `title` varchar(120) not null default '' comment '文章标题',
//            `short_title` varchar(120) not null default '' comment '短标题',
//            `picture` varchar(128) not null default '' comment '文章头图',
//            `summary` varchar(300) not null default '' comment '文章摘要',
//            `category_id` int(10) unsigned not null default '0' comment '类目ID',
//            `source` tinyint(4) not null default '1' comment '来源：1-转载,2-原创,3-翻译',
//            `source_url` varchar(128) not null default '1' comment '原文链接',
//            `official_stat` int(10) unsigned not null default '0' comment '官方状态:0-非官方,1-官方',
//            `topping_stat` int(10) unsigned not null default '0' comment '置顶状态:0-不置顶, 1-置顶',
//            `cream_stat` int(10) unsigned not null default '0' comment '加精状态:0-不加精，1-加精',
//            `status` tinyint(4) not null default '0' comment '状态:0-未发布,1-已发布',
//            `deleted` tinyint(4) not null default '0' comment '是否删除',
//            `create_time` timestamp not null default CURRENT_TIMESTAMP comment '创建时间',
//            `update_time` timestamp not null default CURRENT_TIMESTAMP on update current_timestamp comment '更新时间',
}
