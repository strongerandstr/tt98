package com.tt98.server.controller.index;

import com.tt98.pojo.vo.IndexVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Slf4j
@Controller
public class IndexController {

    @GetMapping(path = {"/","","/index"})
    public String index(Model model){
        IndexVO vo = new IndexVO();
        model.addAttribute("vo", vo);
        return "views/home/index_test";
    }

}
