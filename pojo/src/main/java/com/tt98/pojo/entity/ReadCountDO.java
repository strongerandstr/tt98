package com.tt98.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName(value = "read_count")
public class ReadCountDO {
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long documentId;

    private Integer cnt;

    private Timestamp createTime;

    private Timestamp updateTime;
}
