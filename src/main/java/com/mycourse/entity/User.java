package com.mycourse.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "s_user", uniqueConstraints = {
        @UniqueConstraint(name = "unq_user_username", columnNames = { "username", "deleted" }),
})
@SQLDelete(sql = "UPDATE s_user SET record_status = 'INACTIVE', deleted = now() WHERE id=?")
@Where(clause = "record_status='ACTIVE'")
public class User extends BaseEntity{

    private String fullName;
    private String address;
    private String username;
    private String email;
    private String mobilePhone;
    @ManyToOne
    @JoinColumn(name = "id_s_role", nullable = false)
    private Role role;
    @OneToOne(mappedBy = "user", optional = false)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JsonIgnore
    private UserPassword userPassword;

}
