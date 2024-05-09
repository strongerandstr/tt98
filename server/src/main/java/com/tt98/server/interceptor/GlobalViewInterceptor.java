package com.tt98.server.interceptor;

import com.tt98.server.common.GlobalInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class GlobalViewInterceptor implements AsyncHandlerInterceptor {
    @Autowired
    private GlobalInitService globalInitService;
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView){
        if(!ObjectUtils.isEmpty(modelAndView)){
            if(response.getStatus() == HttpStatus.OK.value()){
                modelAndView.getModel().put("global", globalInitService.globalAttr());
            }
        }
    }

}
