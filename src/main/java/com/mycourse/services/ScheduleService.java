package com.mycourse.services;

import com.mycourse.dao.CourseDao;
import com.mycourse.dao.ParticipantDao;
import com.mycourse.dao.UserDao;
import com.mycourse.dto.MyScheduleDto;
import com.mycourse.entity.Course;
import com.mycourse.entity.Participant;
import com.mycourse.entity.Schedule;
import com.mycourse.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ScheduleService {

    @Autowired private ParticipantDao participantDao;
    @Autowired private UserDao userDao;
    @Autowired private CourseDao courseDao;
    public List<MyScheduleDto> getMySchedules(String username) {

        List<MyScheduleDto> myScheduleDtos = new ArrayList<>();
        User user = userDao.findByUsername(username).get();
        List<Course> courses = courseDao.findByInstructor(user);

        if (!courses.isEmpty()){
            for (Course course : courses){
                for (Schedule schedule : course.getSchedules()){
                    MyScheduleDto scheduleDto = new MyScheduleDto();
                    scheduleDto.setTitle(schedule.getCourse().getName());
                    scheduleDto.setDate(schedule.getLocalDateTime());
                    myScheduleDtos.add(scheduleDto);
                }
            }
        }

        List<Participant> participants = participantDao.findByUser(user);

        for (Participant p : participants){
            for (Schedule schedule : p.getCourse().getSchedules()){
                MyScheduleDto scheduleDto = new MyScheduleDto();
                scheduleDto.setTitle(schedule.getCourse().getName());
                scheduleDto.setDate(schedule.getLocalDateTime());
                myScheduleDtos.add(scheduleDto);
            }
        }
        log.debug("schedules : {}", myScheduleDtos);
        return myScheduleDtos;
    }
}
