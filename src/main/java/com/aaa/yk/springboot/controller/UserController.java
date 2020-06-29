package com.aaa.yk.springboot.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Param
 * @ClassName UserController
 * @Description TODO
 * @Author yk
 * @Date 2020/6/29 0029 15:31
 * @Return
 **/
@Controller
public class UserController {

    /*测试方法*/
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        System.out.println("UserController.hello()");
        return "OK";
    }

    /*测试thymeleaf*/
    @RequestMapping("/testThymeleaf")
    public String  testThymeleaf(Model model){
        /*把数据存入model*/
        model.addAttribute("name","yk");
        /*返回test.html*/
        return "test";
    }

    @RequestMapping("/noAuth")
    public String noAuth(){
        return "noAuth";
    }

    @RequestMapping("/add")
    public String add(){
        return "user/add";
    }

    @RequestMapping("/update")
    public String update(){
        return "user/update";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }


    @RequestMapping("/login")
    public String login(String username,String password, Model model){
        System.out.println("name="+username);
        /*使用shiro编写认证操作*/
        /*1.获取Subject*/
        Subject subject = SecurityUtils.getSubject();

        /*2.封装用户数据*/
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);

        /*3.执行登录方法*/

        try {

            subject.login(token);

            /*登录成功，跳转到test.html
             * redirect代表重定向请求路径*/
            return "redirect:/testThymeleaf";
        } catch (UnknownAccountException e) {
           // e.printStackTrace();
            /*登录失败：用户名不存在*/
            model.addAttribute("msg","用户名不存在");
            return "login";
        }catch (IncorrectCredentialsException e){
            /*登录失败，密码错误*/
            model.addAttribute("msg","登录失败，密码错误");
            return "login";
        }
    }
}
