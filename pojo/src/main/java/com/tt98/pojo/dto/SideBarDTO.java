package com.tt98.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SideBarDTO {
    private String content;
    private String icon;
    private String img;
    private List<SideBarItemDTO> items;
    private Integer style;
    private String subTitle;
    private String title;
    private String url;
}
