package com.aaa.yk.springboot.shiro;

import com.aaa.yk.springboot.model.User;
import com.aaa.yk.springboot.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Param
 * @ClassName UserRealm
 * @Description 自定义Realm
 * @Author yk
 * @Date 2020/6/29 0029 15:15
 * @Return
 **/
public class UserRealm extends AuthorizingRealm {
    /**执行授权逻辑*/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        /*给资源进行授权*/
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        /*添加资源的授权字符串
        * 到数据库查询当前登录用户的授权字符串
        * 获取当前登录用户*/
        Subject subject = SecurityUtils.getSubject();

        User user = (User) subject.getPrincipal();
        User dbUser = userService.selectByPrimaryKey(user.getId());

        info.addStringPermission("user:add");

        return info;
    }

    @Autowired
    private UserService userService;

    /**执行认证逻辑*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token1) throws AuthenticationException {
        System.out.println("执行授权逻辑");
        /*假设数据库的用户名和密码
        String username = "root";
        String password = "123456";*/

        /*编写shiro判断逻辑，判断用户名和密码*/
        /*1.判断用户名*/
        UsernamePasswordToken token = (UsernamePasswordToken) token1;

        /*if (!token.getUsername().equals(username)){
            *//*用户名不存在
             * shiro底层会抛出UnknowAccountException*//*
            return null;
        }
        *//*2.判断密码*//*
        return new SimpleAuthenticationInfo("",password,"");*/
        User user = userService.findByName(token.getUsername());
        if (user == null){
            /*用户名不存在
            * shiro底层会抛出UnknowAccountException*/
            return null;
        }
        /*2.判断密码*/
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
