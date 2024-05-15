package com.tt98.server.controller.article;


import com.tt98.pojo.converter.MarkdownConverter;
import com.tt98.pojo.dto.*;
import com.tt98.pojo.vo.ArticleDetailVO;
import com.tt98.pojo.vo.ArticleEditVO;
import com.tt98.server.common.ReqContext;
import com.tt98.server.service.ArticleReadService;
import com.tt98.server.service.ArticleService;
import com.tt98.server.service.CategoryService;
import com.tt98.server.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    @Autowired
    private ArticleReadService articleReadService;
    @Autowired
    private UserService userService;

    @GetMapping("/edit")
    public String edit(@RequestParam(required = false) Long articleId, Model model){
        ArticleEditVO vo = new ArticleEditVO();

        List<CategoryDTO> categoryDTOList = categoryService.queryAll();
        vo.setCategories(categoryDTOList);

        model.addAttribute("vo", vo);
        return "views/article-edit/index";
    }

    @GetMapping("/detail/{articleId}")
    public String detail(@PathVariable(name = "articleId") Long articleId, Model model){
        ArticleDetailVO vo = new ArticleDetailVO();
        // 文章相关
        ArticleDTO articleDTO = articleReadService.queryFullArticleInfo(articleId, ReqContext.getCurrentId());
        // 返回给前端时，转换为html格式
        articleDTO.setContent(MarkdownConverter.markdownToHtml(articleDTO.getContent()));
        vo.setArticle(articleDTO);

        vo.setOther(new ArticleOtherDTO());

        // TODO: 2024/5/14 评论信息  热门评论  作者信息  侧边推荐信息  作者信息
//        BaseUserInfoDTO baseUserInfoDTO = userService.queryBasicUserInfo(articleDTO.getAuthor());
        UserStatisticInfoDTO author = userService.queryUserInfoWithStatistic(articleDTO.getAuthor());
        vo.setAuthor(author);

        model.addAttribute("vo", vo);
        return "views/article-detail/index";
    }
}
