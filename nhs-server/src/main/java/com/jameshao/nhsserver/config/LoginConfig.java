package com.jameshao.nhsserver.config;

import com.jameshao.nhsserver.intercepter.UserIntercepter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class LoginConfig extends WebMvcConfigurationSupport {

    //拦截器注册成组件
    @Bean
    UserIntercepter userIntercepter(){
        return new UserIntercepter();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        //InterceptorRegistration interceptorRegistration = registry.addInterceptor(new UserIntercepter());
        //注册的对象：从Spring中取组件，
        // 原因：Redis功能
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(userIntercepter());
        interceptorRegistration.addPathPatterns("/**")
                .excludePathPatterns("/login", "login");
    }
}
