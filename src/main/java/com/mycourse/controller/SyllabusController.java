package com.mycourse.controller;

import com.mycourse.dao.SyllabusDao;
import com.mycourse.dto.SyllabusDto;
import com.mycourse.entity.Syllabus;
import com.mycourse.services.SyllabusServices;
import jakarta.validation.Valid;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j @RequestMapping("/syllabus")
public class SyllabusController {

    @Autowired private SyllabusServices services;
    @Autowired private SyllabusDao syllabusDao;

    @GetMapping("/list")
    public String getSyllabusPage(ModelMap modelMap){

        modelMap.addAttribute("syllabus", syllabusDao.findAll());

        return "syllabus/list";
    }

    @GetMapping("/form")
    public String getSyllabusFormPage(ModelMap modelMap){
        modelMap.addAttribute("syllabus", new Syllabus());
        return "syllabus/form";
    }

    @PostMapping("/form")
    public String postSyllabusFormPage(@Valid Syllabus syllabus){
        log.debug("Syllabus : {}", syllabus);
        services.saveSyllabus(syllabus);

        return "redirect:/syllabus/list";
    }

}
