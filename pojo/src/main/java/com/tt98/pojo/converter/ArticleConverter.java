package com.tt98.pojo.converter;

import com.tt98.pojo.Enum.ArticleTypeEnum;
import com.tt98.pojo.Enum.YesOrNoEnum;
import com.tt98.pojo.dto.*;
import com.tt98.pojo.entity.ArticleDO;
import com.tt98.pojo.entity.TagDO;
import com.tt98.pojo.req.ArticlePostReq;

import java.util.List;
import java.util.stream.Collectors;

public class ArticleConverter {

    public static ArticleDTO toDTO(ArticleDO articleDO) {
        if (articleDO == null) {
            return null;
        }
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setAuthor(articleDO.getUserId());
        articleDTO.setArticleId(articleDO.getId());
        articleDTO.setArticleType(articleDO.getArticleType());
        articleDTO.setTitle(articleDO.getTitle());
        articleDTO.setShortTitle(articleDO.getShortTitle());
        articleDTO.setSummary(articleDO.getSummary());
        articleDTO.setCover(articleDO.getPicture());
//        articleDTO.setSourceType(SourceTypeEnum.formCode(articleDO.getSource()).getDesc());
        articleDTO.setSourceUrl(articleDO.getSourceUrl());
        articleDTO.setStatus(articleDO.getStatus());
        articleDTO.setCreateTime(articleDO.getCreateTime().getTime());
        articleDTO.setLastUpdateTime(articleDO.getUpdateTime().getTime());
//        articleDTO.setOfficalStat(articleDO.getOfficalStat());
        articleDTO.setToppingStat(articleDO.getToppingStat());
        articleDTO.setCreamStat(articleDO.getCreamStat());

        // 设置类目id
        articleDTO.setCategory(new CategoryDTO(articleDO.getCategoryId(), null));
        return articleDTO;
    }


    public static List<ArticleDTO> toArticleDTOList(List<ArticleDO> articles){
        return articles.stream().map(ArticleConverter::toDTO).collect(Collectors.toList());
    }

    public static TagDTO toDTO(TagDO tag){
        if(tag == null){
            return null;
        }
        TagDTO tagDTO = new TagDTO();
        tagDTO.setTag(tag.getTagName());
        tagDTO.setTagId(tag.getId());
        tagDTO.setStatus(tag.getStatus());
        return tagDTO;
    }

    public static List<TagDTO> toDTOList(List<TagDO> tags){
        return tags.stream().map(ArticleConverter::toDTO).collect(Collectors.toList());
    }

    public static ArticleDO toArticleDO(ArticlePostReq req, Long authorId) {
        ArticleDO articleDO = new ArticleDO();
        articleDO.setId(req.getArticleId());
        articleDO.setUserId(authorId);
        articleDO.setArticleType(ArticleTypeEnum.valueOf(req.getArticleType().toUpperCase()).getCode());
        articleDO.setStatus(req.getStatus());
        articleDO.setCategoryId(req.getCategoryId());
        articleDO.setDeleted(req.deleted() ? YesOrNoEnum.YES.getCode() : YesOrNoEnum.NO.getCode());
        articleDO.setPicture(req.getCover() == null ? "" : req.getCover());
        articleDO.setSource(req.getSource());
        articleDO.setSourceUrl(req.getSourceUrl());
        articleDO.setSummary(req.getSummary());
        articleDO.setTitle(req.getTitle());

        return articleDO;
    }
}
