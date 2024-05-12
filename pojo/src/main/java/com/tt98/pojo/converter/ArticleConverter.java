package com.tt98.pojo.converter;

import com.tt98.pojo.Enum.ArticleTypeEnum;
import com.tt98.pojo.Enum.YesOrNoEnum;
import com.tt98.pojo.dto.TagDTO;
import com.tt98.pojo.entity.ArticleDO;
import com.tt98.pojo.entity.TagDO;
import com.tt98.pojo.req.ArticlePostReq;

import java.util.List;
import java.util.stream.Collectors;

public class ArticleConverter {

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
