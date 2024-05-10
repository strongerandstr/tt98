package com.tt98.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName(value = "article_tag")
public class ArticleTagDO {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long articleId;

    private Integer tagId;

    private Integer deleted;

    private Timestamp createTime;

    private Timestamp updateTime;

}
