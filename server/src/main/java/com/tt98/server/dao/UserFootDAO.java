package com.tt98.server.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tt98.pojo.dto.PageParamDTO;
import com.tt98.pojo.entity.UserFootDO;
import com.tt98.server.mapper.UserFootMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserFootDAO extends ServiceImpl<UserFootMapper, UserFootDO> {
    public List<Long> listReadArticleByUserId(Long userId, PageParamDTO pageParamDTO) {
        return baseMapper.listReadArticleByUserId(userId, pageParamDTO);
    }

    public List<Long> listCollectedArticlesByUserId(Long userId, PageParamDTO pageParamDTO) {
        return baseMapper.listCollectedArticlesByUserId(userId, pageParamDTO);
    }
}
