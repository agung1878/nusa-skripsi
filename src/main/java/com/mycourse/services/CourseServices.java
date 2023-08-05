package com.mycourse.services;

import com.mycourse.dao.CourseDao;
import com.mycourse.dao.ScheduleDao;
import com.mycourse.dao.UserDao;
import com.mycourse.dto.CourseDto;
import com.mycourse.entity.Course;
import com.mycourse.entity.Schedule;
import com.mycourse.entity.User;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CourseServices {

    @Autowired private CourseDao courseDao;
    @Autowired private ScheduleDao scheduleDao;

    @Transactional
    public void saveCourse(CourseDto courseDto) throws Exception {

        log.debug("==== DTO ==== {}", courseDto);

        String[] arrayDates = courseDto.getDates().split(",");
        List<String> dates = Arrays.asList(arrayDates);

        List<Schedule> schedules = new ArrayList<>();

        Course course = new Course();
        course.setInstructor(courseDto.getInstructor());
        course.setCourseStatus(Course.CourseStatus.AVAILABLE);
        course.setName(courseDto.getName());
        course.setLocation(courseDto.getLocation());
        course.setDescription(courseDto.getDescription());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        for (String date : dates){
            Schedule schedule = new Schedule();
            schedule.setLocalDateTime(LocalDateTime.parse(date+" "+courseDto.getTime(), formatter));
            schedule.setCourse(course);
            schedules.add(schedule);
        }

        scheduleDao.saveAll(schedules);

        course.setSchedules(schedules);

        courseDao.save(course);
    }

}
