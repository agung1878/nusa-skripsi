package com.mycourse.controller.rest;

import com.mycourse.dto.BaseResponseDto;
import com.mycourse.services.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@Slf4j
@RequestMapping("/rest")
@CrossOrigin
public class MySchedulesRestApi {

    @Autowired private ScheduleService services;
    @GetMapping("/my_schedules")
    public ResponseEntity<BaseResponseDto> getMySchedules(Principal principal){
        BaseResponseDto response = new BaseResponseDto();
        try {
            response.setResponseCode("200");
            response.setResponseMessage("SUCCESS");
            response.setData(services.getMySchedules(principal.getName()));
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
