package com.tt98.server.controller.article;


import com.tt98.pojo.dto.CategoryDTO;
import com.tt98.pojo.vo.ArticleEditVO;
import lombok.extern.slf4j.Slf4j;
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

    @GetMapping("/edit")
    public String edit(@RequestParam(required = false) Long articleId, Model model){
        ArticleEditVO vo = new ArticleEditVO();

        // TODO: 2024/5/12 修改这里的逻辑 
        CategoryDTO category1 = new CategoryDTO();
        category1.setCategory("分类1");
        category1.setCategoryId(1L);
        category1.setRank(1);
        category1.setSelected(true);
        category1.setStatus(1);
        List<CategoryDTO> list = new ArrayList<>();
        list.add(category1);
        vo.setCategories(list);

        model.addAttribute("vo", vo);
        return "views/article-edit/index";
    }
}
