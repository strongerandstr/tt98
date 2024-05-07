package com.tt98.pojo.entity;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class ArticleDetailDO {

    private Long articleId;

    private Long version;

    private String content;

    private Integer deleted;

    private Timestamp createTime;

    private Timestamp updateTime;


}
