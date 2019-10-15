package com.man.mandarin.config;
import com.man.mandarin.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;
/**
 * 拦截器配置
 */
@Configuration
public class IntercepterConfig implements WebMvcConfigurer {
    private TokenInterceptor tokenInterceptor;
    //构造方法
    public IntercepterConfig(TokenInterceptor tokenInterceptor){
        this.tokenInterceptor = tokenInterceptor;
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        //admin
        String addPathPattern1="/apis/mandarin/admin/**";
        String [] excludePath1={
                "/apis/mandarin/admin/login"
        };
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns(addPathPattern1)
                .excludePathPatterns(excludePath1);
//        //librarian
//        String addPathPattern2="/apis/mandarin/librarian/**";
//        String [] excludePath2={
//                "/apis/mandarin/librarian/login"
//        };
//        registry.addInterceptor(tokenInterceptor)
//                .addPathPatterns(addPathPattern2)
//                .excludePathPatterns(excludePath2);
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
