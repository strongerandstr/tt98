package com.tt98.server.controller.index;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class IndexController {

    @GetMapping(path = {"/","","/index"})
    public String index(Model model){
        log.info("请求首页");
        return "views/home/index";
    }
}
