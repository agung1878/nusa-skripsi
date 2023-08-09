package com.mycourse.controller.rest;

import com.mycourse.dto.BaseResponseDto;
import com.mycourse.services.CourseServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/rest")
public class CourseRestController {

    @Autowired private CourseServices services;

    @GetMapping("/schedules")
    @CrossOrigin
    public ResponseEntity<BaseResponseDto> getSchedules(@RequestParam String id){
        BaseResponseDto response = new BaseResponseDto();
        try {
            response.setResponseCode("200");
            response.setResponseMessage("SUCCESS");
            response.setData(services.getSchedules(id));
            return ResponseEntity.ok(response);
        } catch (Exception e){
            response.setResponseCode("500");
            response.setResponseMessage(e.getMessage());
            response.setData(null);
            return ResponseEntity.status(500)
                    .body(response);
        }

    }

    @GetMapping("/participants")
    @CrossOrigin
    public ResponseEntity<BaseResponseDto> getParticipants(@RequestParam String id){
        BaseResponseDto response = new BaseResponseDto();
        try {
            response.setResponseCode("200");
            response.setResponseMessage("SUCCESS");
            response.setData(services.getParticipants(id));
            return ResponseEntity.ok(response);
        } catch (Exception e){
            response.setResponseCode("500");
            response.setResponseMessage(e.getMessage());
            response.setData(null);
            return ResponseEntity.status(500)
                    .body(response);
        }

    }

}
