package com.tt98.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tt98.pojo.entity.ArticleDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper extends BaseMapper<ArticleDO> {
}
