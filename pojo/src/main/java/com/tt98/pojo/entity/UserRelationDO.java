package com.tt98.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName(value = "user_relation")
public class UserRelationDO {
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long userId;

    private Long followUserId;

    private Integer followState;

    private Timestamp createTime;

    private Timestamp updateTime;
}
