package com.tt98.server.controller.user;

import com.tt98.pojo.Result;
import com.tt98.pojo.dto.UserLoginDTO;
import com.tt98.server.service.UserService;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@RestController
public class UserLoginController {
    @Autowired
    private UserService userService;


    @PostMapping("login/username")
    public Result<Boolean> login(@RequestParam String username, @RequestParam String password, HttpSession session){
        log.info("login: "+username);
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setUsername(username);
        userLoginDTO.setPassword(password);
        Long id = userService.login(userLoginDTO);
        log.info("login: userId = "+id.toString());
        // 将用户id保存到服务器session中
        session.setAttribute("userId", id);
        // 登录成功后怎么让他刷新页面？
        // 每次渲染页面前，都会执行拦截器的postHandle()方法。此时会更新globalVO的isLogin状态

        return Result.success(Boolean.TRUE);
    }

    @GetMapping("/logout")
    public Result<Boolean> logout(HttpSession session, HttpServletResponse response) throws IOException {
        // session.removeAttribute("userId");
        session.invalidate();
        // TODO: 2024/5/11  bug：退出登陆后，再刷新页面，又回到登录状态

        response.sendRedirect("/index");

        return Result.success(Boolean.TRUE);
    }
}
