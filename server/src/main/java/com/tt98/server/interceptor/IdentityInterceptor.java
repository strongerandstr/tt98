package com.tt98.server.interceptor;


import com.tt98.server.common.ReqContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@Component
public class IdentityInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        log.info("IdentityInterceptor: 拦截请求:" + request.getServletPath());
        HttpSession session = request.getSession(false);// 传入 `false` 表示如果不存在会话，则不创建新的会话
        if(session == null){
            // 未登录
            log.info("IdentityInterceptor: 未登录 session == null" );
        } else {
            // 已登录
            Long userId = (Long) session.getAttribute("userId");
            if(userId != null){
                ReqContext.setCurrentId(userId);
                log.info("IdentityInterceptor: 已登录 userId = " + userId.toString());
            } else {
                log.info("IdentityInterceptor: 未登录 userId == null" );
            }

        }
        return true;
    }


}
