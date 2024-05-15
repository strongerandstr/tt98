package com.tt98.server.common;

import com.tt98.pojo.dto.BaseUserInfoDTO;
import com.tt98.pojo.vo.SiteCntVO;
import com.tt98.server.config.GlobalViewConfig;
import com.tt98.server.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
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


        // TODO: 2024/5/15  设置一些信息
        vo.setSiteStatisticInfo(new SiteCntVO());
        vo.setTodaySiteStatisticInfo(new SiteCntVO());


        BaseUserInfoDTO user = new BaseUserInfoDTO();
        if(ReqContext.getCurrentId() != null){
            vo.setIsLogin(Boolean.TRUE);
            user.setUserId(ReqContext.getCurrentId());
        } else {
            vo.setIsLogin(Boolean.FALSE);
        }

        vo.setUser(user);

        return vo;
    }
}
