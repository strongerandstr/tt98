package com.tt98.pojo.vo;

import lombok.Data;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Data
public class PageListVO<T> {
    /**
     * 列表
     */
    List<T> list;

    /**
     * 是否有更多
     */
    private Boolean hasMore;

    public static <T> PageListVO<T> emptyVO(){
        PageListVO<T> vo = new PageListVO<>();
        vo.setList(Collections.emptyList());
        vo.setHasMore(false);
        return vo;
    }

    public static <T> PageListVO<T> newVO(List<T> list, long pageSize){
        PageListVO<T> vo = new PageListVO<>();
        vo.setList(Optional.ofNullable(list).orElse(Collections.emptyList()));
        vo.setHasMore(vo.getList().size() == pageSize);
        return vo;
    }
}
