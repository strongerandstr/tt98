package com.tt98.server.service;

import com.tt98.pojo.dto.PageParamDTO;

import java.util.List;

public interface UserFootService {
    /**
     * 查询已读文章列表
     * @param userId
     * @param pageParamDTO
     * @return
     */
    List<Long> queryUserReadArticleList(Long userId, PageParamDTO pageParamDTO);

    /**
     * 查询收藏文章列表
     * @param userId
     * @param pageParamDTO
     * @return
     */
    List<Long> queryUserCollectionArticleList(Long userId, PageParamDTO pageParamDTO);
}
