package com.mycourse.controller;

import com.mycourse.dao.CourseDao;
import com.mycourse.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired private CourseDao courseDao;
    @GetMapping()
    public String getDashboardPage(ModelMap modelMap){

        modelMap.addAttribute("availableCourses", courseDao.findAllByCourseStatus(Course.CourseStatus.AVAILABLE));

        return "dashboard";
    }

}
