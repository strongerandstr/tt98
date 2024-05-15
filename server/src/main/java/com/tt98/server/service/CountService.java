package com.tt98.server.service;

import com.tt98.pojo.dto.ArticleFootCountDTO;
import com.tt98.pojo.dto.UserStatisticInfoDTO;

public interface CountService {
    ArticleFootCountDTO queryArticleStatisticInfo(Long articleId);

    UserStatisticInfoDTO queryUserStaticInfo(Long userId);
}
