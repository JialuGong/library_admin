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
//        //amdin
//        String excludePath= "/apis/mandarin/admin/getAllLib";
          String excludePath1= "/apis/mandarin/admin/login";
//        excludePath.add("/apis/mandarin/admin/login");
//        excludePath.add("/apis/mandarin/admin/registerLib");
//        excludePath.add("/apis/mandarin/admin/getAllLib");
//        excludePath.add("/apis/mandarin/admin/editLib");
//        excludePath.add("/apis/mandarin/admin/deleteLib");
//        excludePath.add("/apis/mandarin/admin/modifyBookFine");
//        excludePath.add("/apis/mandarin/admin/getBookFine");
//        excludePath.add("/apis/mandarin/admin/modifyBookPeriod");
//        excludePath.add("/apis/mandarin/admin/getBookPeriod");
//        excludePath.add("/apis/mandarin/admin/modifyReaderDeposit");
//        excludePath.add("/apis/mandarin/admin/getReaderDeposit");
//        excludePath.add("/apis/mandarin/admin/searchLib");
//        excludePath.add("/apis/mandarin/admin/getInfo");

//        //librarian
//        String excludePath2= "/apis/mandarin/librarian/login";

        registry.addInterceptor(tokenInterceptor).excludePathPatterns(excludePath1);
//        registry.addInterceptor(tokenInterceptor).excludePathPatterns(excludePath2);
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
