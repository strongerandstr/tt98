package com.tt98.server.dao;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tt98.pojo.dto.TagDTO;
import com.tt98.pojo.entity.ArticleTagDO;
import com.tt98.server.mapper.ArticleMapper;
import com.tt98.server.mapper.ArticleTagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ArticleTagDAO extends ServiceImpl<ArticleTagMapper, ArticleTagDO> {
    @Autowired
    private ArticleTagMapper articleTagMapper;


    public List<TagDTO> queryArticleTagDetails(Long articleId) {
        return articleTagMapper.listArticleTagDetails(articleId);
    }
}
