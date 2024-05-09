package com.tt98.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDTO {
    private Long articleId;

    private Integer articleType;

    private Long author;
    private String authorAvatar;

    private String authorName;
    private CategoryDTO category;
    private Boolean collected;
    private Boolean commented;
    private String content;
    private ArticleFootCountDTO count;
    private String cover;
    private Integer creamStat;
    private Long createTime;
    private Long lastUpdateTime;
    private Integer officialStat;
    private Boolean praised;

    private List<SimpleUserInfoDTO> praisedUsers;
    private String shortTitle;
    private String sourceType;
    private String sourceUrl;
    private Integer status;
    private String summary;
    private List<TagDTO> tags;
    private String title;
    private Integer toppingStat;

}
