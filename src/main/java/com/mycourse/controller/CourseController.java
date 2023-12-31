package com.mycourse.controller;

import com.mycourse.dao.*;
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
    @Autowired private SyllabusDao syllabusDao;
    @Autowired private CourseServices services;
    @GetMapping("/list")
    public String getListCourse(ModelMap modelMap){

        modelMap.addAttribute("courses", courseDao.findAll());
        return "course/list";
    }

    @GetMapping("/form")
    public String getFormCourse(ModelMap modelMap, @RequestParam String id){

        Optional<Role> role = roleDao.findById("r_instructor");
        modelMap.addAttribute("dto", new CourseDto());
        modelMap.addAttribute("instructors", userDao.findAllByRole(role.get()));
        modelMap.addAttribute("syllabus", syllabusDao.findById(id).get());

        return "course/form";
    }

    @PostMapping("/form")
    public String postFormCourse(@Valid CourseDto courseDto, Principal principal) throws Exception {

        services.saveCourse(courseDto, principal);

        return "redirect:/course/list";
    }

    @GetMapping("/my_course")
    public String getMyCourse(ModelMap modelMap, Principal principal){

        modelMap.addAttribute("courses", services.myCourse(principal));

        return "course/my_course";
    }
}
