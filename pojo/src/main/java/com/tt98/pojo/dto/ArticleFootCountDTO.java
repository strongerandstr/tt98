package com.tt98.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleFootCountDTO {
    private Integer collectionCount;
    private Integer commentCount;
    private Integer praiseCount;
    private Integer readCount;
}
