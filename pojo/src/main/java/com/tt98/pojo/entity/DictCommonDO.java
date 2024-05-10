package com.tt98.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName(value = "dict_common")
public class DictCommonDO {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String typeCode;

    private String dictCode;

    private String dictDesc;

    private Integer sortNo;

    private String remark;

    private Timestamp createTime;

    private Timestamp updateTime;
}
