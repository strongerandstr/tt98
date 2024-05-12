package com.tt98.pojo.dto;

import lombok.Data;

@Data
public class TagSelectDTO {
    /**
     * 类型
     */
    private String selectType;

    /**
     * 描述
     */
    private String selectDesc;

    /**
     * 是否选中
     */
    private Boolean selected;
}
