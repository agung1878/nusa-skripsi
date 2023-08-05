package com.mycourse.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Entity
public class Schedule extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "id_course")
    @JsonManagedReference
    private Course course;
    @DateTimeFormat(pattern = "dd-MM-yyy HH:mm:ss")
    private LocalDateTime localDateTime;

    public enum Attendance{

    }

}
