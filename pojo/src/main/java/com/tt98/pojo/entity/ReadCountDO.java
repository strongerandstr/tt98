package com.tt98.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName(value = "read_count")
public class ReadCountDO {
    private Long documentId;

    private Integer cnt;

    private Timestamp createTime;

    private Timestamp updateTime;
}
