package com.mycourse.dao;

import com.mycourse.entity.Syllabus;
import org.springframework.data.repository.CrudRepository;

public interface SyllabusDao extends CrudRepository<Syllabus, String> {
}
