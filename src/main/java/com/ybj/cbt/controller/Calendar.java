package com.ybj.cbt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author Calendar
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
@Controller
@RequestMapping("/calendar")
public class Calendar {

    @RequestMapping("/index")
    public String index(){
        return "Calendar/calendar";
    }
}
