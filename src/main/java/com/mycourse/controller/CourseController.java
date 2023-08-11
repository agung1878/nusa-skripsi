package com.mycourse.controller;

import com.mycourse.dao.CourseDao;
import com.mycourse.dao.RoleDao;
import com.mycourse.dao.ScheduleDao;
import com.mycourse.dao.UserDao;
import com.mycourse.dto.CourseDto;
import com.mycourse.entity.Role;
import com.mycourse.services.CourseServices;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Optional;

@Controller @RequestMapping("/course")
@Slf4j
public class CourseController {

    @Autowired private UserDao userDao;
    @Autowired private RoleDao roleDao;
    @Autowired private CourseDao courseDao;
    @Autowired private ScheduleDao scheduleDao;
    @Autowired private CourseServices services;
    @GetMapping("/list")
    public String getListCourse(ModelMap modelMap){

        modelMap.addAttribute("courses", courseDao.findAll());
        return "course/list";
    }

    @GetMapping("/form")
    public String getFormCourse(ModelMap modelMap){

        Optional<Role> role = roleDao.findById("r_instructor");
        modelMap.addAttribute("dto", new CourseDto());
        modelMap.addAttribute("instructors", userDao.findAllByRole(role.get()));

        return "course/form";
    }

    @PostMapping("/form")
    public String postFormCourse(@Valid CourseDto courseDto) throws Exception {

        services.saveCourse(courseDto);

        return "redirect:/course/list";
    }

    @GetMapping("/invoice")
    public String getInvoiceCourse(@RequestParam String id, ModelMap modelMap){

        modelMap.addAttribute("course", courseDao.findById(id).get());

        return "course/invoice";
    }

    @PostMapping("/invoice")
    public String addParticipants(@RequestParam String id, Principal principal){
        services.addParticipants(id, principal);
        return "redirect:/dashboard";
    }

    @GetMapping("/my_course")
    public String getMyCourse(ModelMap modelMap, Principal principal){

        modelMap.addAttribute("courses", services.myCourse(principal));

        return "course/my_course";
    }
}
