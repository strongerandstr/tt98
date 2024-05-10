package com.tt98.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.sql.Timestamp;

@Data
@TableName(value = "article_detail")
public class ArticleDetailDO {
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long articleId;

    private Long version;

    private String content;

    private Integer deleted;

    private Timestamp createTime;

    private Timestamp updateTime;


}
