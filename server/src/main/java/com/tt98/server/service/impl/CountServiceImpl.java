package com.tt98.server.service.impl;

import com.tt98.pojo.dto.ArticleFootCountDTO;
import com.tt98.pojo.dto.UserStatisticInfoDTO;
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

    @Override
    public UserStatisticInfoDTO queryUserStaticInfo(Long userId) {
        // TODO: 2024/5/15 统计
        UserStatisticInfoDTO info = new UserStatisticInfoDTO();
        info.setFansCount(9898);
        info.setArticleCount(9898);
        info.setPraiseCount(9898);
        info.setCollectionCount(9898);
        info.setReadCount(9898);
        info.setFansCount(9898);

        return info;
    }
}
