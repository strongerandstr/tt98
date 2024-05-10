package com.tt98.pojo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName(value = "tag")
public class TagDO {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String tagName;

    private Integer tagType;

    private Integer categoryId;

    private Integer status;

    private Integer deleted;

    private Timestamp createTime;

    private Timestamp updateTime;
}
