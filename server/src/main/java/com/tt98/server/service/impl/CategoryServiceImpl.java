package com.tt98.server.service.impl;

import com.tt98.pojo.converter.Converter;
import com.tt98.pojo.dto.CategoryDTO;
import com.tt98.pojo.entity.CategoryDO;
import com.tt98.server.dao.CategoryDAO;
import com.tt98.server.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDAO categoryDAO;
    @Override
    public List<CategoryDTO> queryAll() {
        List<CategoryDO> list = categoryDAO.list();
        return Converter.toDTOList(list);
    }

    @Override
    public String queryCategoryName(Long categoryId) {
        return categoryDAO.getById(categoryId).getCategoryName();
    }
}
