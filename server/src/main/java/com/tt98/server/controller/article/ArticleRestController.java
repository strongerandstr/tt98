package com.tt98.server.controller.article;


import com.tt98.pojo.Result;
import com.tt98.pojo.dto.TagDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/article/api")
public class ArticleRestController {

    @GetMapping("/tag/list")
    public Result<List<TagDTO>> getTagList(@RequestParam(required = false) String key,
                                           @RequestParam(required = false, defaultValue = "1") Integer pageNumber,
                                           @RequestParam(required = false,defaultValue = "10") Integer pageSize){

        return new Result<>();
    }
}
