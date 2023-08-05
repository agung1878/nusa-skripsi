package com.mycourse.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Schedule extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "id_course")
    @JsonManagedReference
    private Course course;
    private LocalDateTime localDateTime;

    public enum Attendance{

    }

}
