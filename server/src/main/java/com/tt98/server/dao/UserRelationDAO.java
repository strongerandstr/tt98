package com.tt98.server.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tt98.pojo.Enum.FollowSelectEnum;
import com.tt98.pojo.dto.FollowUserInfoDTO;
import com.tt98.pojo.dto.PageParamDTO;
import com.tt98.pojo.entity.UserRelationDO;
import com.tt98.server.mapper.UserRelationMapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Repository
public class UserRelationDAO extends ServiceImpl<UserRelationMapper, UserRelationDO> {

    /**
     * 获取关注信息
     * @param userId  登录用户
     * @param followUserId  关注用户
     * @return
     */
    public UserRelationDO getUserRelationByUserId(Long userId, Long followUserId) {
        QueryWrapper<UserRelationDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(UserRelationDO::getUserId, userId)
                .eq(UserRelationDO::getFollowUserId, followUserId)
                .eq(UserRelationDO::getFollowState, FollowSelectEnum.FOLLOW.getCode());

        return baseMapper.selectOne(queryWrapper);
    }

    /**
     * 查询用户的关注列表
     * @param userId
     * @param pageParam
     * @return
     */
    public List<FollowUserInfoDTO> listUserFollows(Long userId, PageParamDTO pageParam) {
        return baseMapper.queryUserFollowList(userId,pageParam);
    }

    public List<FollowUserInfoDTO> listUserFans(Long userId, PageParamDTO pageParam) {
        return baseMapper.queryUserFansList(userId, pageParam);
    }

    /**
     * 查询followUserId与给定的用户列表的关联关系
     * @param followUserId  粉丝用户id
     * @param targetUserId  关注者用户id列表
     * @return
     */
    public List<UserRelationDO> listUserRelations(Long followUserId, Collection<Long> targetUserId) {
        return lambdaQuery().eq(UserRelationDO::getFollowUserId, followUserId)
                .in(UserRelationDO::getUserId, targetUserId).list();
    }

}
