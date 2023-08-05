package com.mycourse.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "s_user_password")
public class UserPassword {

    @Id
    @Column(name = "id_s_user")
    private String id;

    @NotEmpty
    @Column(name = "hashed_password", length = 100, nullable = false)
    public String password;

    @NotNull
    @MapsId
    @OneToOne
    @JoinColumn(name = "id_s_user", insertable = false, updatable = false)
    public User user;


}