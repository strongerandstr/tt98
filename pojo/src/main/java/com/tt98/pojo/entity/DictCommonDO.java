package com.tt98.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName(value = "dict_common")
public class DictCommonDO {
    private String typeCode;

    private String dictCode;

    private String dictDesc;

    private Integer sortNo;

    private String remark;

    private Timestamp createTime;

    private Timestamp updateTime;
}
