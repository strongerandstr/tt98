package com.tt98.server.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tt98.pojo.converter.ArticleConverter;
import com.tt98.pojo.dto.PageParamDTO;
import com.tt98.pojo.dto.TagDTO;
import com.tt98.pojo.entity.TagDO;
import com.tt98.server.mapper.TagMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TagDAO extends ServiceImpl<TagMapper, TagDO> {
    /**
     * 分页查询tags
     */
    public List<TagDTO> getTags(String key, PageParamDTO pageParamDTO){
        LambdaQueryWrapper<TagDO> query = Wrappers.lambdaQuery();
        query.and(StringUtils.isNotBlank(key), v -> v.like(TagDO::getTagName,key));
        if(pageParamDTO != null){
            query.last(PageParamDTO.getLimitSql(pageParamDTO));
        }
        List<TagDO> list = baseMapper.selectList(query);
        return ArticleConverter.toDTOList(list);
    }

    public Integer countTag(String key){
        return lambdaQuery()
                .and(StringUtils.isNotBlank(key), v -> v.like(TagDO::getTagName,key))
                .count()
                .intValue();
    }
}
