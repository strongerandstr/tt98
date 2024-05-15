package com.tt98.server.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tt98.pojo.Enum.YesOrNoEnum;
import com.tt98.pojo.converter.ArticleConverter;
import com.tt98.pojo.dto.ArticleDTO;
import com.tt98.pojo.dto.PageParamDTO;
import com.tt98.pojo.dto.YearArticleDTO;
import com.tt98.pojo.entity.ArticleDO;
import com.tt98.pojo.entity.ArticleDetailDO;
import com.tt98.server.mapper.ArticleDetailMapper;
import com.tt98.server.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class ArticleDAO extends ServiceImpl<ArticleMapper, ArticleDO> {

    @Autowired
    private ArticleDetailMapper articleDetailMapper;

    public Long saveArticleContent(Long articleId, String content) {
        ArticleDetailDO detail = new ArticleDetailDO();
        detail.setContent(content);
        detail.setArticleId(articleId);
        detail.setVersion(1L);
        articleDetailMapper.insert(detail);
        return detail.getId();
    }

    public List<ArticleDO> listArticlesByCategoryId(Long categoryId, PageParamDTO page) {
        if (categoryId != null && categoryId <= 0) {
            // 分类不存在时，表示查所有
            categoryId = null;
        }
        LambdaQueryWrapper<ArticleDO> query = Wrappers.lambdaQuery();
        query.eq(ArticleDO::getDeleted, YesOrNoEnum.NO.getCode());
        // TODO: 2024/5/13 这里被我注释了 根据status搜索

//                .eq(ArticleDO::getStatus, PushStatusEnum.ONLINE.getCode());

        // 如果分页中置顶的四条数据，需要加上官方的查询条件
        // 说明是查询官方的文章，非置顶的文章，只限制全部分类
        // TODO: 2024/5/13 被我注释了
//        if (categoryId == null && page.getPageSize() == PageParamDTO.TOP_PAGE_SIZE) {
//            query.eq(ArticleDO::getOfficalStat, OfficalStatEnum.OFFICAL.getCode());
//        }

        Optional.ofNullable(categoryId).ifPresent(cid -> query.eq(ArticleDO::getCategoryId, cid));
        query.last(PageParamDTO.getLimitSql(page))
                .orderByDesc(ArticleDO::getToppingStat,  ArticleDO::getCreateTime);
        return baseMapper.selectList(query);
    }

    public ArticleDTO queryArticleDetail(Long articleId) {
        ArticleDO articleDO = baseMapper.selectById(articleId);
        if(articleDO == null || Objects.equals(articleDO.getDeleted(), YesOrNoEnum.YES.getCode())){
            return null;
        }

        //查询文章正文
        ArticleDTO dto = ArticleConverter.toDTO(articleDO);
        // TODO: 2024/5/14 审核逻辑？
        ArticleDetailDO detail = findLatestDetail(articleId);
        dto.setContent(detail.getContent());

        return dto;
    }

    private ArticleDetailDO findLatestDetail(Long articleId) {
        LambdaQueryWrapper<ArticleDetailDO> contentQuery = Wrappers.lambdaQuery();
        contentQuery.eq(ArticleDetailDO::getDeleted, YesOrNoEnum.NO.getCode())
                .eq(ArticleDetailDO::getArticleId, articleId)
                .orderByDesc(ArticleDetailDO::getVersion);

        return articleDetailMapper.selectList(contentQuery).get(0);
    }

    public List<YearArticleDTO> listYearArticleByUserId(Long userId) {
        return baseMapper.listYearArticleByUserId(userId);
    }
}
