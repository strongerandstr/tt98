package com.tt98.server.service;


import com.tt98.pojo.dto.UserLoginDTO;

public interface UserService {


    Long login(UserLoginDTO userLoginDTO);
}
