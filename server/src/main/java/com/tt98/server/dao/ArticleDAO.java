package com.tt98.server.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tt98.pojo.entity.ArticleDO;
import com.tt98.server.mapper.ArticleMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleDAO extends ServiceImpl<ArticleMapper, ArticleDO> {

}
