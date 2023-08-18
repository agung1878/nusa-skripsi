package com.mycourse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Presence extends BaseEntity{
    @OneToOne
    @JoinColumn(name = "id_course")
    private Course course;

    @OneToOne
    @JoinColumn(name = "id_schedule")
    private Schedule schedule;

    @OneToOne
    @JoinColumn(name = "id_user_participant")
    private User participant;

    private boolean present = Boolean.FALSE;

}
