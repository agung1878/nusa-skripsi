package com.mycourse.dao;

import com.mycourse.entity.Schedule;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ScheduleDao extends CrudRepository<Schedule, String> {

    List<Schedule> findAllByCourseId(String id);

}
