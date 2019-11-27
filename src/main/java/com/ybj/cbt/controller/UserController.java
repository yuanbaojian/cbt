package com.ybj.cbt.controller;


import com.ybj.cbt.model.User;
import com.ybj.cbt.serverInterface.UserServerInterface;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServerInterface userServer;

    @RequestMapping(value = "/list",  method = RequestMethod.POST)
    public ModelAndView list() {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("user/userList");
        return modelAndView;
    }

      @RequestMapping(value = "/getAllUser", method = RequestMethod.POST)
      public List<User> getAllUser() {
        User loginUser = (User) SecurityUtils.getSubject().getPrincipal();
        List<User> userList=new ArrayList<>();
        try {
            userList=userServer.getAllUser();
        } catch (Exception e) {

        }
        return userList;
      }
}
