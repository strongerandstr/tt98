package com.tt98.pojo.vo;

import com.tt98.pojo.dto.ArticleDTO;
import com.tt98.pojo.dto.FollowUserInfoDTO;
import com.tt98.pojo.dto.TagSelectDTO;
import com.tt98.pojo.dto.UserStatisticInfoDTO;
import lombok.Data;

import java.util.List;

@Data
public class UserHomeVO {
    private String homeSelectType;

    private List<TagSelectDTO> homeSelectTags;

    private PageListVO<FollowUserInfoDTO> followList;

    private String followSelectType;

    private List<TagSelectDTO> followSelectTags;

    private UserStatisticInfoDTO userHome;

    private PageListVO<ArticleDTO> homeSelectList;
}
