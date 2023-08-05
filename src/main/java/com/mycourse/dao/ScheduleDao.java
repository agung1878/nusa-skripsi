package com.mycourse.dao;

import com.mycourse.entity.Schedule;
import org.springframework.data.repository.CrudRepository;

public interface ScheduleDao extends CrudRepository<Schedule, String> {
}
