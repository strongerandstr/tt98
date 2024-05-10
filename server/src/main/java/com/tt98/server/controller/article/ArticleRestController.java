package com.tt98.server.controller.article;


import com.tt98.pojo.Result;
import com.tt98.pojo.dto.PageParamDTO;
import com.tt98.pojo.dto.TagDTO;
import com.tt98.pojo.vo.PageVO;
import com.tt98.server.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/article/api")
public class ArticleRestController {
    @Autowired
    private TagService tagService;

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
}
