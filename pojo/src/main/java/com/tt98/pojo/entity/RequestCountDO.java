package com.tt98.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@TableName(value = "request_count")
public class RequestCountDO {
    private String host;

    private Integer cnt;

    private Date date;

    private Timestamp createTime;

    private Timestamp updateTime;
}
