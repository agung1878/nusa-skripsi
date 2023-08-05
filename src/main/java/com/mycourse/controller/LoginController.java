package com.mycourse.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage(Authentication authentication){
        if(authentication != null && authentication.isAuthenticated()) return "redirect:/dashboard";
        return "login";
    }

}
