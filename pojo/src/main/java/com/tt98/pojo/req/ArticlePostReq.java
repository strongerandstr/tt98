package com.tt98.pojo.req;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class ArticlePostReq implements Serializable {

    private Long articleId;

    private String title;

    private String shortTitle;

    private Long categoryId;

    private Set<Long> tagIds;

    /**
     * 简介
     */
    private String summary;

    /**
     * 正文内容
     */
    private String content;

    /**
     * 封面
     */
    private String cover;

    private String articleType;

    private Integer source;

    /**
     * 状态: 0-未发布， 1-已发布
     */
    private Integer status;

    /**
     * 原文链接
     */
    private String sourceUrl;

    /**
     * POST 发表  SAVE 暂存  DELETE 删除
     */
    private String actionType;

    private Long columnId;
}
