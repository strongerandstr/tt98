package com.tt98.server.service.impl;

import com.tt98.pojo.converter.ArticleConverter;
import com.tt98.pojo.dto.ArticleDTO;
import com.tt98.pojo.dto.BaseUserInfoDTO;
import com.tt98.pojo.dto.PageParamDTO;
import com.tt98.pojo.entity.ArticleDO;
import com.tt98.pojo.vo.PageListVO;
import com.tt98.server.dao.ArticleDAO;
import com.tt98.server.dao.ArticleTagDAO;
import com.tt98.server.service.ArticleReadService;
import com.tt98.server.service.CategoryService;
import com.tt98.server.service.CountService;
import com.tt98.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleReadServiceImpl implements ArticleReadService {

    @Autowired
    private ArticleDAO articleDAO;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ArticleTagDAO articleTagDAO;
    @Autowired
    private CountService countService;
    @Autowired
    private UserService userService;

    /**
     * 查询文章列表
     *
     * @param categoryId
     * @param page
     * @return
     */
    @Override
    public PageListVO<ArticleDTO> queryArticlesByCategory(Long categoryId, PageParamDTO page) {
        List<ArticleDO> records = articleDAO.listArticlesByCategoryId(categoryId, page);

//        List<ArticleDTO> articleDTOS = ArticleConverter.toArticleDTOList(records);

//        return PageListVO.newVO(articleDTOS, page.getPageSize());
        return buildArticleListVO(records, page.getPageSize());
    }

    public PageListVO<ArticleDTO> buildArticleListVO(List<ArticleDO> records, long pageSize) {
        List<ArticleDTO> result = records.stream().map(this::fillArticleRelatedInfo).collect(Collectors.toList());
        return PageListVO.newVO(result, pageSize);
    }

    /**
     * 补全文章的阅读计数、作者、分类、标签等信息
     *
     * @param record
     * @return
     */
    private ArticleDTO fillArticleRelatedInfo(ArticleDO record) {
        ArticleDTO dto = ArticleConverter.toDTO(record);
        // 分类信息
        dto.getCategory().setCategory(categoryService.queryCategoryName(record.getCategoryId()));
        // 标签列表
        dto.setTags(articleTagDAO.queryArticleTagDetails(record.getId()));
        // 阅读计数统计
        dto.setCount(countService.queryArticleStatisticInfo(record.getId()));
        // 作者信息
        BaseUserInfoDTO author = userService.queryBasicUserInfo(dto.getAuthor());
        dto.setAuthorName(author.getUserName());
        dto.setAuthorAvatar(author.getPhoto());
        return dto;
    }
}
