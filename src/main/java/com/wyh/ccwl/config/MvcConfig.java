package com.wyh.ccwl.config;

import com.wyh.ccwl.util.AuthorityInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

//导入配置
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    private static List<String> EXCLDUE_PATH = Arrays.asList("/addTrainingRecords","/login",
            "/index.html","/css/**","/img/**","/js/**","/layui/**","/video/**","/view/**");

    //添加自定义拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorityInterceptor()).addPathPatterns("/**")
                //配置不拦截的路径
                .excludePathPatterns(EXCLDUE_PATH);
    }

    @Bean
    public AuthorityInterceptor authorityInterceptor(){
        return  new AuthorityInterceptor();
    }
}