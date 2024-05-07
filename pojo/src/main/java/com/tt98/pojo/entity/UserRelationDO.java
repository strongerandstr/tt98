package com.tt98.pojo.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserRelationDO {
    private Long userId;

    private Long followUserId;

    private Integer followState;

    private Timestamp createTime;

    private Timestamp updateTime;
}
