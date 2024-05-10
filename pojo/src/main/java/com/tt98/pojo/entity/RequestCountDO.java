package com.tt98.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@TableName(value = "request_count")
public class RequestCountDO {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String host;

    private Integer cnt;

    private Date date;

    private Timestamp createTime;

    private Timestamp updateTime;
}
