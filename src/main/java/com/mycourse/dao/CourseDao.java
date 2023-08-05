package com.mycourse.dao;

import com.mycourse.entity.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseDao extends CrudRepository<Course, String> {
}
