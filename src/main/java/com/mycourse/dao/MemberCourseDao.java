package com.mycourse.dao;

import com.mycourse.entity.MemberCourse;
import org.springframework.data.repository.CrudRepository;

public interface MemberCourseDao extends CrudRepository<MemberCourse, String> {
}
