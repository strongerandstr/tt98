package com.tt98.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName(value = "user_foot")
public class UserFootDO {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long userId;

    private Long documentId;

    private Integer documentType;

    private Long documentUserId;

    private Integer collectionStat;

    private Integer readStat;

    private Integer commentStat;

    private Integer praiseStat;

    private Timestamp createTime;

    private Timestamp updateTime;

}
