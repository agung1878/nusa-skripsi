package com.mycourse.dao;

import com.mycourse.entity.Schedule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ScheduleDao extends CrudRepository<Schedule, String>, PagingAndSortingRepository<Schedule, String> {

    List<Schedule> findAllByCourseId(String id);

}
