package com.tt98.server.controller.index;

import com.tt98.pojo.vo.IndexVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
public class IndexController {
    @Autowired
    private IndexRecommendHelper indexRecommendHelper;

    @GetMapping(path = {"/","","/index"})
    public String index(Model model, HttpServletRequest request){
        String category = request.getParameter("category");
        IndexVO vo = indexRecommendHelper.buildIndexVO(category);

        model.addAttribute("vo", vo);
        return "views/home/index_test";
    }

}
