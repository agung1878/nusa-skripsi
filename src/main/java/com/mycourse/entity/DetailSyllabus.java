package com.mycourse.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class DetailSyllabus extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "id_header_syllabus")
    @JsonManagedReference
    private HeaderSyllabus headerSyllabus;
    private String name;

}
