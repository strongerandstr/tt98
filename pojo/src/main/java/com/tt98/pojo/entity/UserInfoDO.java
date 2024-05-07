package com.tt98.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName(value = "user_info", autoResultMap = true)
public class UserInfoDO {
    private Long userId;

    private String userName;

    private String photo;

    private String position;

    private String company;

    private String profile;

    private Integer userRole;

    private String extend;

    @TableField(typeHandler = JacksonTypeHandler.class)
    private IpInfo ip;

    public IpInfo getIp(){
        if(ip == null){
            return new IpInfo();
        }
        return ip;
    }

    private Integer deleted;

    private Timestamp createTime;

    private Timestamp updateTime;

}
