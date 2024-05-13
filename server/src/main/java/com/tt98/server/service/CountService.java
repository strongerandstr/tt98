package com.tt98.server.service;

import com.tt98.pojo.dto.ArticleFootCountDTO;

public interface CountService {
    ArticleFootCountDTO queryArticleStatisticInfo(Long articleId);
}
