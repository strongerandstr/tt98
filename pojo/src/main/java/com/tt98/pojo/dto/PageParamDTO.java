package com.tt98.pojo.dto;

import lombok.Data;

/**
 * 数据库分页查询参数封装类
 */
@Data
public class PageParamDTO {
    public static final Long DEFAULT_PAGE_NUM = 1L;
    public static final Long DEFAULT_PAGE_SIZE = 10L;
    public static final Long TOP_PAGE_SIZE = 4L;

    private long pageNum;
    private long pageSize;

    private long offset;
    private long limit;

    public static PageParamDTO newPageInstance(Long pageNum, Long pageSize){
        if(pageNum == null || pageSize == null){
            return null;
        }
        final PageParamDTO pageParamDTO = new PageParamDTO();
        pageParamDTO.pageNum = pageNum;
        pageParamDTO.pageSize = pageSize;

        pageParamDTO.offset = (pageNum-1) * pageSize;
        pageParamDTO.limit = pageSize;
        return pageParamDTO;
    }

    public static PageParamDTO newPageInstance(){
        return newPageInstance(DEFAULT_PAGE_NUM, DEFAULT_PAGE_SIZE);
    }

    public static String getLimitSql(PageParamDTO pageParamDTO){
        return String.format("limit %s,%s",pageParamDTO.offset, pageParamDTO.limit);
    }

}
