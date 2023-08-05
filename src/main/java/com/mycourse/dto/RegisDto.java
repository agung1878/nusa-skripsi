package com.mycourse.dto;

import com.mycourse.entity.Role;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class RegisDto {

    private String fullName;
    private String address;
    private String username;
    private String email;
    private String mobilePhone;
    private String password;
    private Role role;

}
