package com.coocap.uni.sso.client.system.config;

import com.coocap.uni.sso.client.filter.LoginFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.servlet.Filter;

/**
 * Created by zhouqing on 2018/3/20.
 * 过滤器注册配置
 */
@Configuration
public class FilterConfig {

    @Value("${ssoServerLoginUrl}")
    private String ssoServerLoginUrl;

    @Value("${ssoServerVerifyUrl}")
    private String ssoServerVerifyUrl;

    @Bean("loginFilter")
    public Filter loginFilter() {
        LoginFilter loginFilter = new LoginFilter();
        loginFilter.setSsoServerLoginUrl(ssoServerLoginUrl);
        loginFilter.setSsoServerVerifyUrl(ssoServerVerifyUrl);
        return loginFilter;
    }

    @Bean
    @Order(1)
    public FilterRegistrationBean loginFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(loginFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("loginFilter");
        return registration;
    }


}
