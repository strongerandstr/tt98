package com.tt98.server.controller.article;


import com.tt98.pojo.vo.ArticleEditVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Slf4j
@RequestMapping("/article")
@Controller
public class ArticleViewController {

    @GetMapping("/edit")
    public String edit(@RequestParam(required = false) Long articleId, Model model){
        ArticleEditVO vo = new ArticleEditVO();
        model.addAttribute("vo", vo);
        return "views/article-edit/index";
    }
}
