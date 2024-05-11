package com.tt98.pojo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
@TableName(value = "user")
public class UserDO {

    @TableId(type = IdType.AUTO)
    private Long id;                 // 主键ID

    private String thirdAccountId;   // 第三方用户ID
    private String username;         //
    private String password;
    private int loginType;           // 登录方式：0-微信登录  1-账号登陆
    private int deleted;             // 是否删除
    private Timestamp createTime;    // 创建时间
    private Timestamp updateTime;    // 修改时间
}
