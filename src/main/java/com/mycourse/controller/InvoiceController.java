package com.mycourse.controller;

import com.mycourse.dao.CourseDao;
import com.mycourse.dao.SyllabusDao;
import com.mycourse.dao.UserDao;
import com.mycourse.services.CourseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
public class InvoiceController {

    @Autowired private CourseDao courseDao;
    @Autowired private UserDao userDao;
    @Autowired private CourseServices services;

    @GetMapping("/invoice")
    public String getInvoiceCourse(@RequestParam String id, ModelMap modelMap, Principal principal){

        modelMap.addAttribute("course", courseDao.findById(id).get());
        modelMap.addAttribute("user", userDao.findByUsername(principal.getName()).get());
        modelMap.addAttribute("date", LocalDate.now());
        modelMap.addAttribute("totalPrice", services.totalPrice(id));

        return "course/invoice";
    }

//    @PostMapping("/invoice")
//    public String createInvoice(@RequestParam String id, Principal principal){
//        services.createInvoice(id, principal);
//        return "redirect:/course/my_course";
//    }

}
