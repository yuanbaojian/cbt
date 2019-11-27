package com.ybj.cbt.auth;

import com.ybj.cbt.auth.MyRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/***
 * @Description
 * @param
 * @return
 * @author baojian.yuan
 * @date 2019/9/30
 */
@Configuration
public class ShiroConfig {
    @Bean
    MyRealm myRealm() {
        return new MyRealm();
    }

    @Bean
    DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(myRealm());
        return manager;
    }

    @Bean
    ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager());
        //定义登录url， 系统启动直接访问这个url
        bean.setLoginUrl("/toLogin");
        //登录成功后  url
        bean.setSuccessUrl("/");
        Map<String, String> map = new LinkedHashMap<>();
        map.put("/toLogin", "anon");
        map.put("/login", "anon");
        map.put("/assets/**", "anon");
        map.put("/**", "authc");
        bean.setFilterChainDefinitionMap(map);
        return bean;
    }
}

