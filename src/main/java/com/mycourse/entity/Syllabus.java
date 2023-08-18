package com.mycourse.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Syllabus extends BaseEntity{

    private String name;
    private String description;
    @OneToMany(mappedBy = "syllabus", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<HeaderSyllabus> headerSyllabusList = new ArrayList<>();
    private BigDecimal price;
    private double ppn;

}
