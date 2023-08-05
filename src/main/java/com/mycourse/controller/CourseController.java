package com.mycourse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller @RequestMapping("/course")
public class CourseController {

    @GetMapping("/list")
    public String getListCourse(){
        return "/course/list";
    }

    @GetMapping("/form")
    public String getFormCourse(){
        return "/course/form";
    }

}
