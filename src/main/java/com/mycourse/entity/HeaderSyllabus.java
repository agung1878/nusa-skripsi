package com.mycourse.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class HeaderSyllabus extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "id_syllabus")
    @JsonManagedReference
    private Syllabus syllabus;
    private String name;
    @OneToMany(mappedBy = "headerSyllabus", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<DetailSyllabus> detailSyllabusList = new ArrayList<>();

}
