package com.tt98.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tt98.pojo.converter.ArticleConverter;
import com.tt98.pojo.dto.ArticleDTO;
import com.tt98.pojo.dto.PageParamDTO;
import com.tt98.pojo.entity.ArticleDO;
import com.tt98.pojo.req.ArticlePostReq;
import com.tt98.pojo.vo.PageListVO;
import com.tt98.server.common.util.IdUtil;
import com.tt98.server.common.util.NumUtil;
import com.tt98.server.common.ReqContext;
import com.tt98.server.dao.ArticleDAO;
import com.tt98.server.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private TransactionTemplate transactionTemplate;
    @Autowired
    private ArticleDAO articleDAO;



    @Override
    public Long saveArticle(ArticlePostReq req) {
        // 要写哪个表？ article, article_detail, article_tag
        Long authorId = ReqContext.getCurrentId();
        ArticleDO article = ArticleConverter.toArticleDO(req, authorId);
        // TODO: 2024/5/12 content包含图片链接，需要转存
        String content = req.getContent();
        // 事务
        return transactionTemplate.execute(new TransactionCallback<Long>() {
            @Override
            public Long doInTransaction(TransactionStatus status) {
                Long articleId;
                if(NumUtil.nullOrZero(req.getArticleId())){
                    articleId = insertArticle(article, content, req.getTagIds());
                } else {
                    // TODO: 2024/5/12 更新文章
                    articleId = 520L;
//                    articleId = updateArticle(article, content, req.getTagIds());
                }

                if(req.getColumnId() != null){
                    // TODO: 2024/5/12 更新文章对应的专栏信息
                }
                return articleId;
            }
        });

    }


//    private Long updateArticle(ArticleDO article, String content, Set<Long> tagIds) {
//
//    }


    private Long insertArticle(ArticleDO article, String content, Set<Long> tagIds) {
        // 三张表:article, article_detail, article_tag
        // TODO: 2024/5/12 审核逻辑

        // 使用分布式id生成文章id
        // FIXME: 2024/5/12 生成的 id : 2413351284279297 数据库报错：Out of range value for column 'id' at row 1
//        Long articleId = IdUtil.genId();
//        log.info("插入文章:文章id = " + articleId);
//        article.setId(articleId);
        articleDAO.saveOrUpdate(article);
        Long articleId = article.getId();
        // 保存文章内容
        // TODO: 2024/5/12 保存content到article_detail表
        articleDAO.saveArticleContent(articleId, content);

        // 发布文章, 阅读数+1
        // TODO: 2024/5/12 发布文章,阅读数+1


        // TODO: 2024/5/12 发布文章创建事件等内容

        return articleId;
    }
}
