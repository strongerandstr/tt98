package com.tt98.server.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tt98.pojo.entity.CategoryDO;
import com.tt98.server.mapper.CategoryMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDAO extends ServiceImpl<CategoryMapper, CategoryDO> {


}
