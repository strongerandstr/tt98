package com.tt98.server.dao;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tt98.pojo.entity.ArticleTagDO;
import com.tt98.server.mapper.ArticleTagMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleTagDAO extends ServiceImpl<ArticleTagMapper, ArticleTagDO> {

}
