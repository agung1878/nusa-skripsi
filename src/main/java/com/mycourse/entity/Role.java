package com.mycourse.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "s_role",
        uniqueConstraints = {
                @UniqueConstraint(name = "unq_role_code", columnNames = {"code", "deleted"}),
        })
@SQLDelete(sql = "UPDATE s_role SET record_status = 'INACTIVE', deleted=now() WHERE id=?")
@Where(clause = "record_status='ACTIVE'")
public class Role extends BaseEntity{

    @Column(length = 100, nullable = false)
    public String name;

    @Column(length = 100, nullable = false, unique = true)
    public String code;

    @ManyToMany
    @JoinTable(
            name="s_role_permission",
            joinColumns=@JoinColumn(name="id_role", nullable=false, columnDefinition = "VARCHAR(36)"),
            inverseJoinColumns=@JoinColumn(name="id_permission", nullable=false, columnDefinition = "VARCHAR(36)")
    )
    private List<Permission> permissions = new ArrayList<>();


}
