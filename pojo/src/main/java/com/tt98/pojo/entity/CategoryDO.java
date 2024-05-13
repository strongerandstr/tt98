package com.tt98.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName(value = "category")
public class CategoryDO {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String categoryName;

    private Integer status;

    @TableField("`rank`")
    private Integer rank;

    private Integer deleted;

    private Timestamp createTime;

    private Timestamp updateTime;
}

