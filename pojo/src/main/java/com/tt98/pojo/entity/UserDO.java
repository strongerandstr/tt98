package com.tt98.pojo.entity;


import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class UserDO {
    private long id;                 // 主键ID
    private String thirdAccountId;   // 第三方用户ID
    private String username;         //
    private String password;
    private int loginType;           // 登录方式：0-微信登录  1-账号登陆
    private int deleted;             // 是否删除
    private Timestamp createTime;    // 创建时间
    private Timestamp updateTime;    // 修改时间
}
