package com.tt98.pojo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 关注者用户信息
 */
@Data
public class FollowUserInfoDTO implements Serializable {
    /**
     * 当前登录的用户 与 这个用户的 关联关系id
     */
    private Long relationId;

    /**
     * true:表示当前登录用户关注了这个用户
     * false:表示当前登录用户没有关注这个用户
     */
    private Boolean followed;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户头像
     */
    private String avatar;
}
