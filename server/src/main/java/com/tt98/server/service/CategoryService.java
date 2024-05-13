package com.tt98.server.service;

import com.tt98.pojo.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> queryAll();

    String queryCategoryName(Long categoryId);
}
