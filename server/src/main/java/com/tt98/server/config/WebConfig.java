package com.tt98.server.config;

import com.tt98.server.interceptor.GlobalViewInterceptor;
import com.tt98.server.interceptor.IdentityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private GlobalViewInterceptor globalViewInterceptor;
    @Autowired
    private IdentityInterceptor identityInterceptor;
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(globalViewInterceptor)
                .addPathPatterns("/**");

        registry.addInterceptor(identityInterceptor)
                .addPathPatterns("/**");
    }
}
