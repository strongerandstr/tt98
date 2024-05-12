package com.tt98.server.controller.user;

import com.tt98.pojo.Enum.FollowSelectEnum;
import com.tt98.pojo.Enum.HomeSelectEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import com.tt98.pojo.vo.UserHomeVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/home")
    public String home(@RequestParam(name = "userId") Long userId,
                       @RequestParam(name = "followSelectType", required = false) String followSelectType,
                       @RequestParam(name = "homeSelectType", required = false) String homeSelectType,
                       Model model){
        log.info("查看用户主页: userId = " + userId);
        UserHomeVO vo = new UserHomeVO();
        vo.setHomeSelectType(StringUtils.isBlank(homeSelectType) ? HomeSelectEnum.ARTICLE.getCode() : homeSelectType);
        vo.setFollowSelectType(StringUtils.isBlank(followSelectType) ? FollowSelectEnum.FOLLOW.getCode() : followSelectType);

        model.addAttribute("vo", vo);
        return "views/user/index";
    }
}
