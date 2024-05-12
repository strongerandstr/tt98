package com.tt98.server.service;

import com.tt98.pojo.req.ArticlePostReq;

public interface ArticleService {
    Long saveArticle(ArticlePostReq req);
}
