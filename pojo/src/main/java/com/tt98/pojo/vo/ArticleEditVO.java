package com.tt98.pojo.vo;

import com.tt98.pojo.dto.ArticleDTO;
import com.tt98.pojo.dto.CategoryDTO;
import com.tt98.pojo.dto.TagDTO;
import lombok.Data;

import java.util.List;

@Data
public class ArticleEditVO {
    private ArticleDTO article;
    private List<CategoryDTO> categories;
    private List<TagDTO> tags;
}
