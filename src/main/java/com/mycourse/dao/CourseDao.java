package com.mycourse.dao;

import com.mycourse.entity.Course;
import com.mycourse.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseDao extends CrudRepository<Course, String> {

    List<Course> findAllByCourseStatus(Course.CourseStatus courseStatus);
    List<Course> findByInstructor(User user);

}
