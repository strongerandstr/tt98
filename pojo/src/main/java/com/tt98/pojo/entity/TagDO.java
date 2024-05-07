package com.tt98.pojo.entity;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class TagDO {
    private String tagName;

    private Integer tagType;

    private Integer categoryId;

    private Integer status;

    private Integer deleted;

    private Timestamp createTime;

    private Timestamp updateTime;
}
