package com.ybj.cbt.controller;


import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.List;


@Controller
@RequestMapping("/teachContent")
public class TeachContentController {
    /***
     * @Description  显示某个学生当前的所有课程
     * @param
     * @return java.util.List<com.atoz.cbtsys.model.TeachContent>
     * @author baojian.yuan
     * @date 2019/9/23
     */
//    @RequestMapping("/showCourseOfStudentAsCalendar")
//    @ResponseBody
//    public List<  TeachContent> shownAsCalendar(
//            @RequestParam(value = "firstDayDate") String firstDayDate
//    ) throws ParseException {
//        User user=(User) SecurityUtils.getSubject().getPrincipal();
//        List<TeachContent> teachContent=null;
//        teachContent=teachContentServiceI.getTeachContentListByStudentId(user.getUserId(), firstDayDate);
//        return teachContent;
//    }





}
