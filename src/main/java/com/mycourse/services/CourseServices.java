package com.mycourse.services;

import com.mycourse.dao.*;
import com.mycourse.dto.CourseDto;
import com.mycourse.dto.ScheduleDto;
import com.mycourse.entity.*;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
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
    @Autowired private MemberCourseDao memberCourseDao;
    @Autowired private InvoiceDao invoiceDao;

    @Transactional
    public void saveCourse(CourseDto courseDto, Principal principal) throws Exception {

        String[] arrayDates = courseDto.getDates().split(",");
        List<String> dates = Arrays.asList(arrayDates);

        List<Schedule> schedules = new ArrayList<>();
        List<MemberCourse> memberCourses = new ArrayList<>();

        Course course = new Course();
        course.setInstructor(courseDto.getInstructor());
        course.setName(courseDto.getName());
        course.setLocation(courseDto.getLocation());
        course.setDescription(courseDto.getDescription());
        course.setSyllabus(courseDto.getSyllabus());
        course.setClient(userDao.findByUsername(principal.getName()).get());
        course.setStatus(Course.Status.WAITING);

        for (MemberCourse memberCourse : courseDto.getMemberCourses()){
            memberCourse.setCourse(course);
            memberCourses.add(memberCourse);

        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        for (String date : dates){
            Schedule schedule = new Schedule();
            schedule.setLocalDateTime(LocalDateTime.parse(date+" "+courseDto.getTime(), formatter));
            schedule.setCourse(course);
            schedules.add(schedule);
        }

        scheduleDao.saveAll(schedules);
        memberCourseDao.saveAll(memberCourses);

        course.setMemberCourses(memberCourses);
        course.setSchedules(schedules);

        courseDao.save(course);
    }

    @Transactional
    public void addParticipants(String idCourse, Principal principal){

        Optional<Course> optionalCourse = courseDao.findById(idCourse);

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

    public List<MemberCourse> getParticipants(String id){
        Course course = courseDao.findById(id).get();
        return course.getMemberCourses();
    }

    public List<Course> myCourse(Principal principal){
       List<Course> courses = new ArrayList<>();
       User user = userDao.findByUsername(principal.getName()).get();

       if (!courseDao.findByInstructor(user).isEmpty()){
           for (Course course : courseDao.findByInstructor(user)){
               courses.add(course);
           }
       }

       courses = courseDao.findByClient(user);

       return courses;

    }

    public void setCourseApprove(String id) {

        Course course = courseDao.findById(id).get();
        course.setStatus(Course.Status.APPROVED);
        courseDao.save(course);

    }

    public BigDecimal totalPrice(String id) {
        Course course = courseDao.findById(id).get();
        BigDecimal total = course.getSyllabus().getPrice().add(calculatePercentage(course.getSyllabus().getPrice(), course.getSyllabus().getPpn()));
        return total;
    }

    private BigDecimal calculatePercentage(BigDecimal obtained, double percentage) {
        BigDecimal percentageAmount = obtained.multiply(BigDecimal.valueOf((double)percentage/100));
        return percentageAmount;
    }

    public void createInvoice(String id) {
        Course course = courseDao.findById(id).get();
        BigDecimal total = course.getSyllabus().getPrice().add(calculatePercentage(course.getSyllabus().getPrice(), course.getSyllabus().getPpn()));
        Invoice invoice = new Invoice();
        invoice.setCourse(course);
        invoice.setPaid(Boolean.TRUE);
        invoice.setTotal(total);
        invoice.setInvoiceNumber(course.getName() +" - " + course.getClient().getUsername() +" - "+ LocalDate.now());

        Invoice invoice1 = invoiceDao.save(invoice);

        course.setInvoice(invoice1);
        courseDao.save(course);
    }
}
