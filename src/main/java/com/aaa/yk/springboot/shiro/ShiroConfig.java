package com.aaa.yk.springboot.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Param
 * @ClassName ShiroConfig
 * @Description shiro的配置类
 * @Author yk
 * @Date 2020/6/29 0029 15:18
 * @Return
 **/
@Configuration
public class ShiroConfig {
    /*创建ShiroFilterFactoryBean*/

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        /*设置安全管理器*/
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        /*添加shiro内置过滤器
        *   常用的过滤器：
        *       anon：无需认证（登录）可以访问
        *       authc：必须认证才可以访问
        *       user：如果使用rememberMe的功能可以直接访问
        *       perms：该资源必须得到资源权限才可以访问
        *       role：该资源必须得到角色权限才可以访问*/
        Map<String ,String> filterMap = new LinkedHashMap<String, String>();
        filterMap.put("/testThymeleaf","anon");
        filterMap.put("/add","authc");
        filterMap.put("/update","anon");
        filterMap.put("/login","anon");
        filterMap.put("/hello","anon");
        /*授权过滤器
        * 注意，当授权拦截后，shiro会自动跳转到未授权页面*/
        filterMap.put("/add","perms[user:add]");
        filterMap.put("/update","perms[user:update]");

        filterMap.put("/","authc");

        /*修改调整的登录页面*/
        shiroFilterFactoryBean.setLoginUrl("/toLogin");

        /*设置未授权提示页面*/
        shiroFilterFactoryBean.setUnauthorizedUrl("/noAuth");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    /**
     * @Qualifier: 找到下面的userRealm
     * 创建DefaultWebSecurityManager*/
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm")UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        /*关联Realm*/
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /**创建Realm
     * bean注解的作用：让方法返回的对象放入spring的环境，以便给上面的方法使用*/
    @Bean(name = "userRealm")
    public UserRealm getUserRealm(){
        return new UserRealm();
    }

    /*配置ShiroDialect,用于thymeleaf和shiro标签配合使用*/

    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
