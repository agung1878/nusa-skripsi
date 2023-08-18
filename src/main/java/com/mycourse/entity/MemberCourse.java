package com.mycourse.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class MemberCourse extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "id_course")
    @JsonManagedReference
    private Course course;
    private String name;
    private String position;

}
