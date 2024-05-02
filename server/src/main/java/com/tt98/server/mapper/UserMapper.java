package com.tt98.server.mapper;

import com.tt98.pojo.entity.UserDO;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("select * from user where user_name = #{username};")
    UserDO getUserByName(String username);

    void insert(UserDO user);
}
