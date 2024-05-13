package com.tt98.server.service;

import com.tt98.pojo.dto.ArticleDTO;
import com.tt98.pojo.dto.PageParamDTO;
import com.tt98.pojo.req.ArticlePostReq;
import com.tt98.pojo.vo.PageListVO;

import java.util.List;

public interface ArticleService {
    Long saveArticle(ArticlePostReq req);

//    List<ArticleDTO> queryArticlesByCategoryId(Long categoryId);


}
