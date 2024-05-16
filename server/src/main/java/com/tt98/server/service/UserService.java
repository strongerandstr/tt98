package com.tt98.server.service;


import com.tt98.pojo.dto.BaseUserInfoDTO;
import com.tt98.pojo.dto.UserLoginDTO;
import com.tt98.pojo.dto.UserStatisticInfoDTO;
import com.tt98.pojo.req.UserInfoSaveReq;

public interface UserService {


    Long login(UserLoginDTO userLoginDTO);

    BaseUserInfoDTO queryBasicUserInfo(Long authorId);

    UserStatisticInfoDTO queryUserInfoWithStatistic(Long userId);

    void saveUserInfo(UserInfoSaveReq req);
}
