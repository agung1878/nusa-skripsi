package com.mycourse.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Course extends BaseEntity{

    @Column(nullable = false, length = 100)
    private String name;
    @Column(nullable = false)
    private String description;
    @OneToMany(mappedBy = "course")
    @JsonBackReference
    private List<Schedule> schedules = new ArrayList<>();
    @OneToMany(mappedBy = "course")
    @JsonBackReference
    private List<Participant> participants = new ArrayList<>();
    @Column(nullable = false, length = 150)
    private String location;
    @OneToOne
    @JoinColumn(name = "id_user_instructor", nullable = false)
    private User instructor;
    @Enumerated(value = EnumType.STRING)
    private CourseStatus courseStatus;
    public enum CourseStatus{
        AVAILABLE, GOING_ON, CLOSED
    }

}
