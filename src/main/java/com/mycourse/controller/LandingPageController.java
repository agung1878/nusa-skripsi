package com.mycourse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller @RequestMapping("/")
public class LandingPageController {

    @GetMapping
    public String getLandingPage(){
        return "landing";
    }

}
