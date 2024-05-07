package com.tt98.pojo.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ArticleDO {

    private Long userId;

    private Integer articleType;

    private String title;

    private String shortTitle;

    private String picture;

    private String summary;

    private Long categoryId;

    private Integer source;

    private String sourceURL;

    private Long officialStat;

    private Long toppingStat;

    private Long creamStat;

    private Integer status;

    private Integer deleted;

    private Timestamp createTime;

    private Timestamp updateTime;


}