package com.tt98.pojo.dto;

import lombok.Data;

@Data
public class BaseUserInfoDTO extends BaseDTO {
    private Long userId;
    private String userName;
    /**
     * 用户角色: admin  normal
     */
    private String role;

    /**
     * 用户头像
     */
    private String photo;

    /**
     * 用户简介
     */
    private String profile;

    /**
     * 个人职位
     */
    private String position;

    /**
     * 扩展字段
     */
    private String extend;
    private Integer deleted;
    private String region;

}
