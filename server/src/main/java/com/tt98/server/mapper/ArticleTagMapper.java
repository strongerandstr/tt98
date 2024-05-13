package com.tt98.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tt98.pojo.dto.TagDTO;
import com.tt98.pojo.entity.ArticleTagDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleTagMapper extends BaseMapper<ArticleTagDO> {
    /**
     * 查询文章标签
     *
     * @param articleId
     * @return
     */
    List<TagDTO> listArticleTagDetails(@Param("articleId") Long articleId);
}
