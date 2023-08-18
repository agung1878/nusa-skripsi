package com.mycourse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
public class Invoice extends BaseEntity{

    @OneToOne
    @JoinColumn(name = "id_course")
    private Course course;
    private String invoiceNumber;
    private BigDecimal total;
    private boolean paid = Boolean.FALSE;

}
