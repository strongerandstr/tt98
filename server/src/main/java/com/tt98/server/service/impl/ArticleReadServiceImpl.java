package com.tt98.server.service.impl;

import com.tt98.pojo.Enum.HomeSelectEnum;
import com.tt98.pojo.Enum.StatusEnum;
import com.tt98.pojo.converter.ArticleConverter;
import com.tt98.pojo.dto.ArticleDTO;
import com.tt98.pojo.dto.BaseUserInfoDTO;
import com.tt98.pojo.dto.CategoryDTO;
import com.tt98.pojo.dto.PageParamDTO;
import com.tt98.pojo.entity.ArticleDO;
import com.tt98.pojo.entity.UserFootDO;
import com.tt98.pojo.vo.PageListVO;
import com.tt98.server.common.util.ExceptionUtil;
import com.tt98.server.dao.ArticleDAO;
import com.tt98.server.dao.ArticleTagDAO;
import com.tt98.server.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
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
    @Autowired
    private UserFootService userFootService;
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

    @Override
    public ArticleDTO queryFullArticleInfo(Long articleId, Long userId) {
        ArticleDTO articleDTO = queryDetailArticleInfo(articleId);
        // TODO: 2024/5/14 阅读计数要修改
        // 文章阅读计数+1
//        countService.incrArticleReadCount(articleDTO.getAuthor(), articleId);

        // TODO: 2024/5/14 文章操作标记
        articleDTO.setPraised(false);
        articleDTO.setCommented(false);
        articleDTO.setCollected(false);
        if(userId != null){
            // 判断是否点赞、评论、收藏
//            UserFootDO foot = userFootService.saveOrUpdateUserFoot(DocumentTypeEnum)
        }


        // 更新文章统计计数
        articleDTO.setCount(countService.queryArticleStatisticInfo(articleId));
        // TODO: 2024/5/14 设置文章的点赞列表


        return articleDTO;
    }

    @Override
    public PageListVO<ArticleDTO> queryArticlesByUserAndType(Long userId, PageParamDTO pageParamDTO, HomeSelectEnum select) {
        List<ArticleDO> records = null;
        if(select == HomeSelectEnum.ARTICLE){
            records = articleDAO.listArticlesByUserId(userId, pageParamDTO);
        } else if(select == HomeSelectEnum.READ){
            //用户的阅读记录
            List<Long> articleIds = userFootService.queryUserReadArticleList(userId, pageParamDTO);
            records = CollectionUtils.isEmpty(articleIds) ? Collections.emptyList() : articleDAO.listByIds(articleIds);
            // TODO: 2024/5/16 这里是不是可以优化一下，直接在数据库排序
            records = sortByIds(articleIds, records);
        } else if(select == HomeSelectEnum.COLLECTION){
            // 用户的收藏列表
            List<Long> articleIds = userFootService.queryUserCollectionArticleList(userId, pageParamDTO);
            records = CollectionUtils.isEmpty(articleIds) ? Collections.emptyList() : articleDAO.listByIds(articleIds);
            records = sortByIds(articleIds, records);
        }

        if(CollectionUtils.isEmpty(records)){
            return PageListVO.emptyVO();
        }
        return buildArticleListVO(records, pageParamDTO.getPageSize());
    }

    // TODO: 2024/5/16 这个排序逻辑，后期要改用数据库的排序 
    private List<ArticleDO> sortByIds(List<Long> articleIds, List<ArticleDO> records) {
        List<ArticleDO> articleDOS = new ArrayList<>();
        Map<Long, ArticleDO> articleDOMap = records.stream().collect(Collectors.toMap(ArticleDO::getId, t -> t));
        articleIds.forEach(articleId -> {
            if (articleDOMap.containsKey(articleId)) {
                articleDOS.add(articleDOMap.get(articleId));
            }
        });
        return articleDOS;
    }

    private ArticleDTO queryDetailArticleInfo(Long articleId) {
        ArticleDTO articleDTO = articleDAO.queryArticleDetail(articleId);
        if(articleDTO == null){
            throw ExceptionUtil.of(StatusEnum.ARTICLE_NOT_EXISTS, articleId);
        }
        //更新分类相关信息
        CategoryDTO categoryDTO = articleDTO.getCategory();
        categoryDTO.setCategory(categoryService.queryCategoryName(categoryDTO.getCategoryId()));

        //更新标签信息
        articleDTO.setTags(articleTagDAO.queryArticleTagDetails(articleId));
        return articleDTO;
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
