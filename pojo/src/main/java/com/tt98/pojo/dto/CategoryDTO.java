package com.tt98.pojo.dto;

import com.tt98.pojo.Enum.PushStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO implements Serializable {
    public static final String DEFAULT_TOTAL_CATEGORY = "全部";
    public static final CategoryDTO DEFAULT_CATEGORY = new CategoryDTO(0L, "全部");
    public static CategoryDTO EMPTY = new CategoryDTO(-1L, "illegal");

    private String category;

    private Long categoryId;

    private Integer rank;

    private Boolean selected;

    private Integer status;

    public CategoryDTO(Long categoryId, String category){
        this(categoryId, category, 0);
    }

    public CategoryDTO(Long categoryId, String category, Integer rank){
        this.categoryId = categoryId;
        this.category = category;
        this.status = PushStatusEnum.ONLINE.getCode();
        this.rank = rank;
        this.selected = false;
    }


}
