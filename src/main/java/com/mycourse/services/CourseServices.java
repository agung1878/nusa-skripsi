package com.mycourse.services;

import com.mycourse.dao.CourseDao;
import com.mycourse.dao.ParticipantDao;
import com.mycourse.dao.ScheduleDao;
import com.mycourse.dao.UserDao;
import com.mycourse.dto.CourseDto;
import com.mycourse.entity.*;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CourseServices {

    final static DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    @Autowired private CourseDao courseDao;
    @Autowired private ScheduleDao scheduleDao;
    @Autowired private UserDao userDao;
    @Autowired private ParticipantDao participantDao;

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

    @Transactional
    public void addParticipants(String idCourse, Principal principal){

        log.debug("ID COURSE = {}", idCourse);
        log.debug("Principal name = {}", principal.getName());
        log.debug("USER = {}", userDao.findByUsername(principal.getName()).get());
        Optional<Course> optionalCourse = courseDao.findById(idCourse);
        if (optionalCourse.isPresent()){
            Participant participant = new Participant();
            participant.setUser(userDao.findByUsername(principal.getName()).get());
            participant.setCourse(optionalCourse.get());
            participant.setPaidPayment(Boolean.TRUE);
            participantDao.save(participant);
            optionalCourse.get().getParticipants().add(participant);
            courseDao.save(optionalCourse.get());
        }

    }

    public List<ScheduleDto> getSchedules(String id){
        List<Schedule> schedules = scheduleDao.findAllByCourseId(id);
        List<ScheduleDto> scheduleDtos = new ArrayList<>();
        for (Schedule schedule : schedules){
            ScheduleDto scheduleDto = new ScheduleDto();
            scheduleDto.setDatetime(schedule.getLocalDateTime().format(CUSTOM_FORMATTER));
            scheduleDto.setCourseName(schedule.getCourse().getName());
            scheduleDtos.add(scheduleDto);
        }
        log.debug("Schedules = {}", scheduleDtos);
        return scheduleDtos;
    }

    public List<Participant> getParticipants(String id){
        List<Participant> participants = participantDao.findAllByCourseId(id);
        return participants;
    }

}
