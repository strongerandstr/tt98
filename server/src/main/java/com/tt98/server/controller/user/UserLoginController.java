package com.tt98.server.controller.user;

import com.tt98.pojo.Result;
import com.tt98.pojo.dto.UserLoginDTO;
import com.tt98.server.service.UserService;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/login")
public class UserLoginController {
    @Autowired
    private UserService userService;


    @PostMapping("/username")
    public Result<Boolean> login(@RequestParam String username, @RequestParam String password){
        log.info("login: "+username);
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setUsername(username);
        userLoginDTO.setPassword(password);
        userService.login(userLoginDTO);

        return Result.success(Boolean.TRUE);
    }
}
