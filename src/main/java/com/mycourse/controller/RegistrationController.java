package com.mycourse.controller;

import com.mycourse.dto.RegisDto;
import com.mycourse.services.UserServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired private UserServices userServices;
    @GetMapping("/registration")
    public String getRegistrationPage(ModelMap modelMap){
        modelMap.addAttribute("regis", new RegisDto());
        return "registration";
    }

    @PostMapping("/registration")
    public String postRegistration(ModelMap modelMap, @Valid RegisDto regisDto){

        userServices.registration(regisDto);

        return "redirect:login";
    }

}
