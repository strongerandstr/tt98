package com.tt98.server.controller.article;


import com.tt98.pojo.dto.CategoryDTO;
import com.tt98.pojo.vo.ArticleEditVO;
import com.tt98.server.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@RequestMapping("/article")
@Controller
public class ArticleViewController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/edit")
    public String edit(@RequestParam(required = false) Long articleId, Model model){
        ArticleEditVO vo = new ArticleEditVO();

        List<CategoryDTO> categoryDTOList = categoryService.queryAll();
        vo.setCategories(categoryDTOList);

        model.addAttribute("vo", vo);
        return "views/article-edit/index";
    }
}
