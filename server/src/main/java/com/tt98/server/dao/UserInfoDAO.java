package com.tt98.server.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tt98.pojo.Enum.YesOrNoEnum;
import com.tt98.pojo.entity.UserInfoDO;
import com.tt98.server.mapper.UserInfoMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserInfoDAO extends ServiceImpl<UserInfoMapper, UserInfoDO> {

    public UserInfoDO getByUserId(Long userId) {
        LambdaQueryWrapper<UserInfoDO> query = Wrappers.lambdaQuery();
        query.eq(UserInfoDO::getUserId, userId)
                .eq(UserInfoDO::getDeleted, YesOrNoEnum.NO.getCode());
        return baseMapper.selectOne(query);
    }
}
