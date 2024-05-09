package com.tt98.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private String category;

    private Long categoryId;

    private Integer rank;

    private Boolean selected;

    private Integer status;
}
