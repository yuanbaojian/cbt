package com.ybj.cbt.controller;


import com.ybj.cbt.serverInterface.UserServerInterface;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class LoginController extends  BaseController {

    @Autowired
    UserServerInterface userServer;


    @RequestMapping("/login")
    public ModelAndView login(
            @RequestParam(value = "userId") String userId
            ,@RequestParam(value = "password") String password
    ) {
        //获取subject
        Subject subject = SecurityUtils.getSubject();
        //封装用户信息

        ModelAndView modelAndView=new ModelAndView();

        UsernamePasswordToken token=new UsernamePasswordToken(userId, password);
        try {
            subject.login(token);
            System.out.println("登录成功!");
            modelAndView.setViewName("home/index");
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("用户不存在，登录失败!");
            modelAndView.setViewName("home/login");
        } catch(IncorrectCredentialsException e){
            System.out.println("密码错误，登录失败");
            modelAndView.setViewName("home/login");
        }
        return modelAndView;
    }

    @RequestMapping("/")
    public ModelAndView index(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("home/index");
        return  modelAndView;
    }


    @RequestMapping("/toLogin")
    public ModelAndView toLogin(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("home/login");
        return  modelAndView;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void logout(HttpServletResponse response) throws IOException {

        ModelAndView modelAndView=new ModelAndView();
        Subject subject = SecurityUtils.getSubject();
        // 如果已经登录，则跳转到管理首页
        if(subject != null){
            SecurityUtils.getSubject().logout();
        }
        response.sendRedirect("/toLogin");
    }

}
