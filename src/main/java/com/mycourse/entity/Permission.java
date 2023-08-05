package com.mycourse.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Data
@Entity
@Table(name = "s_permission",
        uniqueConstraints = {
                @UniqueConstraint(name = "unq_permission_value", columnNames = {"value", "deleted"}),
        })
@SQLDelete(sql = "UPDATE s_permission SET record_status = 'INACTIVE', deleted = now() WHERE id=?")
@Where(clause = "record_status='ACTIVE'")
public class Permission extends BaseEntity{

    @Column(length = 100, nullable = false)
    public String label;

    @Column(length = 100, nullable = false)
    public String value;

    @Column(columnDefinition = "TEXT")
    private String description;

}
