package com.mycourse.dto;

import com.mycourse.entity.MemberCourse;
import com.mycourse.entity.Syllabus;
import com.mycourse.entity.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CourseDto {

    private String name;
    private String description;
    private User instructor;
    private Syllabus syllabus;
    private String time;
    private String location;
    private String dates;
    private List<MemberCourse> memberCourses = new ArrayList<>();

}
