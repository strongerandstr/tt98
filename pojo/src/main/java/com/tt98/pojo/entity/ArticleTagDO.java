package com.tt98.pojo.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ArticleTagDO {
    private Long articleId;

    private Integer tagId;

    private Integer deleted;

    private Timestamp createTime;

    private Timestamp updateTime;

}
