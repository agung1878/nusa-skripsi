package com.mycourse.controller;

import com.mycourse.dao.RoleDao;
import com.mycourse.dao.UserDao;
import com.mycourse.dto.RegisDto;
import com.mycourse.entity.User;
import com.mycourse.services.UserServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller @RequestMapping("/users")
public class UserController {

    @Autowired private RoleDao roleDao;
    @Autowired private UserDao userDao;
    @Autowired private UserServices userServices;

    @GetMapping("/list")
    public String getUsers(ModelMap modelMap){
        modelMap.addAttribute("users", userDao.findAll());
        return "/users/list";
    }

    @GetMapping("/form")
    public String getFormUsers(ModelMap modelMap){

        modelMap.addAttribute("user", new RegisDto());
        modelMap.addAttribute("roles", roleDao.findAll());

        return "/users/form";
    }

    @PostMapping("/form")
    public String postFormUsers(@Valid RegisDto regisDto){

        userServices.registration(regisDto);

        return "redirect:/users/list";
    }

}
