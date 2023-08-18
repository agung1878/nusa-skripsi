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
    @OneToOne
    @JoinColumn(name = "id_client")
    private User client;
    @Column(nullable = false)
    private String description;
    @OneToOne
    @JoinColumn(name = "id_syllabus")
    private Syllabus syllabus;
    @OneToMany(mappedBy = "course")
    @JsonBackReference
    private List<Schedule> schedules = new ArrayList<>();
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<MemberCourse> memberCourses = new ArrayList<>();
    @Column(nullable = false, length = 150)
    private String location;
    @OneToOne
    @JoinColumn(name = "id_user_instructor", nullable = false)
    private User instructor;
    @OneToOne
    @JoinColumn(name = "id_invoice")
    private Invoice invoice;
    @Enumerated(EnumType.STRING)
    private Status status;
    public enum Status {
        WAITING, APPROVED, REJECTED
    }

}
