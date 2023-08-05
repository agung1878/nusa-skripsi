package com.mycourse.services;

import com.mycourse.dao.CourseDao;
import com.mycourse.dto.CourseDto;
import com.mycourse.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServices {

    @Autowired private CourseDao courseDao;

    public void saveCourse(Course course) throws Exception {

        try {
            Course course1 = new Course();
            course1.setCourseStatus(Course.CourseStatus.AVAILABLE);
            course1.setName(course.getName());
            course1.setDescription(course.getDescription());
            course1.setLocation(course.getLocation());
            course1.setParticipants(course.getParticipants());
            course1.setInstructor(course.getInstructor());
            course1.setSchedules(course.getSchedules());
        }catch (Exception e){
            throw new Exception();
        }
    }

}
