package com.mycourse.dto;

import com.mycourse.entity.HeaderSyllabus;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class SyllabusDto {

    private String name;
    private String description;
    private BigDecimal price;
    private double ppn;
    private List<HeaderSyllabus> headers = new ArrayList<>();
}
