package com.tt98.server.service.impl;

import com.tt98.pojo.Enum.FollowStateEnum;
import com.tt98.pojo.dto.FollowUserInfoDTO;
import com.tt98.pojo.dto.PageParamDTO;
import com.tt98.pojo.entity.UserRelationDO;
import com.tt98.pojo.vo.PageListVO;
import com.tt98.server.common.util.MapUtils;
import com.tt98.server.dao.UserRelationDAO;
import com.tt98.server.service.UserRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserRelationServiceImpl implements UserRelationService {
    @Autowired
    private UserRelationDAO userRelationDAO;

    @Override
    public PageListVO<FollowUserInfoDTO> getUserFollowList(Long userId, PageParamDTO pageParam) {
        List<FollowUserInfoDTO> userRelationList = userRelationDAO.listUserFollows(userId, pageParam);
        return PageListVO.newVO(userRelationList, pageParam.getPageSize());
    }



    @Override
    public PageListVO<FollowUserInfoDTO> getUserFansList(Long userId, PageParamDTO pageParam) {
        List<FollowUserInfoDTO> userRelationList = userRelationDAO.listUserFans(userId, pageParam);
        return PageListVO.newVO(userRelationList, pageParam.getPageSize());
    }

    @Override
    public void updateUserFollowRelationId(PageListVO<FollowUserInfoDTO> followList, Long loginUserId) {
        if (loginUserId == null) {
            followList.getList().forEach(r -> {
                r.setRelationId(null);
                r.setFollowed(false);
            });
            return;
        }

        // 判断登录用户与给定的用户列表的关注关系
        Set<Long> userIds = followList.getList().stream().map(FollowUserInfoDTO::getUserId).collect(Collectors.toSet());
        if (CollectionUtils.isEmpty(userIds)) {
            return;
        }

        List<UserRelationDO> relationList = userRelationDAO.listUserRelations(loginUserId, userIds);
        Map<Long, UserRelationDO> relationMap = MapUtils.toMap(relationList, UserRelationDO::getUserId, r -> r);
        followList.getList().forEach(follow -> {
            UserRelationDO relation = relationMap.get(follow.getUserId());
            if (relation == null) {
                follow.setRelationId(null);
                follow.setFollowed(false);
            } else if (Objects.equals(relation.getFollowState(), FollowStateEnum.FOLLOW.getCode())) {
                follow.setRelationId(relation.getId());
                follow.setFollowed(true);
            } else {
                follow.setRelationId(relation.getId());
                follow.setFollowed(false);
            }
        });
    }
}
