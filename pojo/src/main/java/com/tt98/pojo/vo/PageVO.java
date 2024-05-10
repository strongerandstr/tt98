package com.tt98.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageVO<T> {
    private List<T> list;

    private Long pageNum;

    private Long pageSize;

    private Long pageTotal;

    private Long total;

    public PageVO(List<T> list, long pageSize, long pageNum, long total){
        this.list = list;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
        this.pageTotal = (long) Math.ceil((double) total / pageSize);
    }

    public static <T> PageVO<T> build(List<T> list, long pageSize, long pageNum, long total) {
        return new PageVO<>(list, pageSize, pageNum, total);
    }
}
