package com.tt98.server.service;

import com.tt98.pojo.Enum.HomeSelectEnum;
import com.tt98.pojo.dto.ArticleDTO;
import com.tt98.pojo.dto.PageParamDTO;
import com.tt98.pojo.vo.PageListVO;

public interface ArticleReadService {
    PageListVO<ArticleDTO> queryArticlesByCategory(Long categoryId, PageParamDTO newPageInstance);

    ArticleDTO queryFullArticleInfo(Long articleId, Long userId);

    PageListVO<ArticleDTO> queryArticlesByUserAndType(Long userId, PageParamDTO pageParamDTO, HomeSelectEnum select);
}
