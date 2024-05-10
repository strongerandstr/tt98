package com.tt98.pojo.converter;

import com.tt98.pojo.dto.TagDTO;
import com.tt98.pojo.entity.TagDO;

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
}
