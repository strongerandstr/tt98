package com.tt98.server.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tt98.pojo.entity.ArticleDetailDO;
import com.tt98.server.mapper.ArticleDetailMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleDetailDAO extends ServiceImpl<ArticleDetailMapper, ArticleDetailDO> {

}
