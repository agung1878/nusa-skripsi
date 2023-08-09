package com.mycourse.dao;

import com.mycourse.entity.Course;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseDao extends CrudRepository<Course, String> {

    List<Course> findAllByCourseStatus(Course.CourseStatus courseStatus);

}
