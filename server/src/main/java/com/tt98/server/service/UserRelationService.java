package com.tt98.server.service;

import com.tt98.pojo.dto.FollowUserInfoDTO;
import com.tt98.pojo.dto.PageParamDTO;
import com.tt98.pojo.vo.PageListVO;

public interface UserRelationService {

    PageListVO<FollowUserInfoDTO> getUserFollowList(Long userId, PageParamDTO pageParam);

    PageListVO<FollowUserInfoDTO> getUserFansList(Long userId, PageParamDTO pageParam);

    void updateUserFollowRelationId(PageListVO<FollowUserInfoDTO> followList, Long loginUserId);
}
