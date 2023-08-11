package com.mycourse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CertificateController {

    @GetMapping("/e_certificate")
    public String getCertificatePage(){
        return "users/e_certificate";
    }

}
