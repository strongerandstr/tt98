package com.tt98.server.service.impl;

import com.tt98.pojo.dto.UserLoginDTO;
import com.tt98.pojo.entity.UserDO;
import com.tt98.server.mapper.UserMapper;
import com.tt98.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public Boolean login(UserLoginDTO userLoginDTO) {
        // 检查数据库有没有这个用户
        UserDO user = userMapper.getUserByName(userLoginDTO.getUsername());

        if(user == null){
            // 注册一个账户
            saveUser(userLoginDTO.getUsername(), userLoginDTO.getPassword());
            return Boolean.TRUE;
        }
        if(!user.getPassword().equals(userLoginDTO.getPassword())){
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    // 新增用户
    public void saveUser(String name, String password){
        UserDO user = UserDO.builder()
                .username(name)
                .password(password)
                .createTime(new Timestamp(System.currentTimeMillis()))
                .updateTime(new Timestamp(System.currentTimeMillis()))
                .build();

        userMapper.insert(user);
    }
}
