package com.tt98.server.controller.article;


import com.github.hui.quick.plugin.qrcode.util.json.JsonUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.tt98.pojo.Enum.DocumentTypeEnum;
import com.tt98.pojo.Enum.NotifyTypeEnum;
import com.tt98.pojo.Enum.OperateTypeEnum;
import com.tt98.pojo.Enum.StatusEnum;
import com.tt98.pojo.Result;
import com.tt98.pojo.dto.PageParamDTO;
import com.tt98.pojo.dto.TagDTO;
import com.tt98.pojo.entity.ArticleDO;
import com.tt98.pojo.entity.UserFootDO;
import com.tt98.pojo.req.ArticlePostReq;
import com.tt98.pojo.vo.PageVO;
import com.tt98.server.common.CommonConstants;
import com.tt98.server.common.NotifyMsgEvent;
import com.tt98.server.common.ReqContext;
import com.tt98.server.common.util.SpringUtil;
import com.tt98.server.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

@Slf4j
@RestController
@RequestMapping("/article/api")
public class ArticleRestController {
    @Autowired
    private TagService tagService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleReadService articleReadService;
    @Autowired
    private UserFootService userFootService;
    @Autowired
    private RabbitmqService rabbitmqService;
    @GetMapping("/tag/list")
    public Result<PageVO<TagDTO>> getTagList(@RequestParam(required = false) String key,
                                           @RequestParam(required = false, defaultValue = "1") Integer pageNumber,
                                           @RequestParam(required = false,defaultValue = "10") Integer pageSize){

        PageVO<TagDTO> pageVo = tagService.queryTags(key, PageParamDTO.newPageInstance(pageNumber.longValue(), pageSize.longValue()));
        if(pageVo == null){
            log.info("pageVo == null");
        }

        return Result.success(pageVo);
    }

    @PostMapping("/post")
    public Result<Long> post(@RequestBody ArticlePostReq req, HttpServletResponse response){
        articleService.saveArticle(req);
        return Result.success(Long.valueOf(0L));
    }


    public Result<Boolean> favor(@RequestParam(name = "articleId") Long articleId,
                                 @RequestParam(name = "type") Integer type) throws IOException, TimeoutException {
        OperateTypeEnum operate = OperateTypeEnum.fromCode(type);
        if(operate == OperateTypeEnum.EMPTY){
            return Result.fail(StatusEnum.ILLEGAL_ARGUMENTS_MIXED, type + "非法");
        }
        // 检查是否存在
        ArticleDO article = articleReadService.queryBasicArticle(articleId);
        if(article == null){
            return Result.fail(StatusEnum.ILLEGAL_ARGUMENTS_MIXED, "文章不存在");
        }

        UserFootDO foot = userFootService.saveOrUpdateUserFoot(DocumentTypeEnum.ARTICLE, articleId, article.getUserId(),
                ReqContext.getCurrentId(), operate);

        // 点赞、收藏消息
        NotifyTypeEnum notifyType = OperateTypeEnum.getNotifyType(operate);

        // 点赞消息走RabbitMQ, 其他走Java内置消息机制
        if(notifyType.equals(NotifyTypeEnum.PRAISE) && rabbitmqService.enabled()){
            rabbitmqService.publishMsg(
                    CommonConstants.EXCHANGE_NAME_DIRECT,
                    BuiltinExchangeType.DIRECT,
                    CommonConstants.QUERE_KEY_PRAISE,
                    JsonUtil.toStr(foot));
        } else {
            Optional.ofNullable(notifyType).ifPresent(notify -> SpringUtil.publishEvent(new NotifyMsgEvent<>(this, notify, foot)));
        }

        return Result.success(true);
    }
}
