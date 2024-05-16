package com.tt98.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tt98.pojo.dto.PageParamDTO;
import com.tt98.pojo.entity.UserFootDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserFootMapper extends BaseMapper<UserFootDO> {
    List<Long> listReadArticleByUserId(@Param("userId") Long userId, @Param("pageParamDTO") PageParamDTO pageParamDTO);

    List<Long> listCollectedArticlesByUserId(@Param("userId") Long userId, @Param("pageParamDTO") PageParamDTO pageParamDTO);
}
