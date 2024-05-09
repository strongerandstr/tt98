package com.tt98.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SideBarItemDTO {
    private String img;
    private String name;
    private List<Integer> tags;
    private Integer time;
    private String title;
    private String url;
    private RateVisitDTO visit;
}
