package com.mycourse.dto;

import com.mycourse.entity.User;
import lombok.Data;

@Data
public class CourseDto {

    private String name;
    private String description;
    private User instructor;
    private String time;
    private String location;
    private String dates;

}
