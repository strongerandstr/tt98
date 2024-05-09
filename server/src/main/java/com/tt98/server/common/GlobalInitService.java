package com.tt98.server.common;

import com.tt98.pojo.dto.BaseUserInfoDTO;
import com.tt98.server.config.GlobalViewConfig;
import com.tt98.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GlobalInitService {
    @Value("dev")
    private String env;

    private UserService userService;
    @Autowired
    private GlobalViewConfig globalViewConfig;

    public GlobalVO globalAttr(){
        GlobalVO vo = new GlobalVO();
        vo.setSiteInfo(globalViewConfig);
        vo.setUser(new BaseUserInfoDTO());
        vo.setIsLogin(Boolean.TRUE);
        return vo;
    }
}
