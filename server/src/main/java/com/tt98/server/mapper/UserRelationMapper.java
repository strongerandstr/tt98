package com.tt98.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tt98.pojo.dto.FollowUserInfoDTO;
import com.tt98.pojo.dto.PageParamDTO;
import com.tt98.pojo.entity.UserRelationDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserRelationMapper extends BaseMapper<UserRelationDO> {
    /**
     * 查询 userId 关注的用户
     * @param userId
     * @param pageParam
     * @return
     */
    List<FollowUserInfoDTO> queryUserFollowList(@Param("userId") Long userId, @Param("pageParam") PageParamDTO pageParam);

    /**
     * 查询关注userId的用户
     * @param userId
     * @param pageParam
     * @return
     */
    List<FollowUserInfoDTO> queryUserFansList(@Param("userId") Long userId, @Param("pageParam") PageParamDTO pageParam);
}
