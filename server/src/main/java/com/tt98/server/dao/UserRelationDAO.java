package com.tt98.server.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tt98.pojo.Enum.FollowSelectEnum;
import com.tt98.pojo.entity.UserRelationDO;
import com.tt98.server.mapper.UserRelationMapper;
import org.springframework.stereotype.Repository;

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
}
