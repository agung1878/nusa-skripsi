package com.mycourse.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MyScheduleDto {

    private String title;
    private LocalDateTime date;

}
