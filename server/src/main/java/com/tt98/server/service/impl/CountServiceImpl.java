package com.tt98.server.service.impl;

import com.tt98.pojo.dto.ArticleFootCountDTO;
import com.tt98.server.service.CountService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CountServiceImpl implements CountService {


    @Override
    public ArticleFootCountDTO queryArticleStatisticInfo(Long articleId) {

        ArticleFootCountDTO info = new ArticleFootCountDTO();
        // TODO: 2024/5/13 统计数量 
        info.setPraiseCount(520);
        info.setCollectionCount(520);
        info.setCommentCount(0);
        info.setReadCount(520);
        return info;
    }
}
