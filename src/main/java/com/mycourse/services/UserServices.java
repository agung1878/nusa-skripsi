package com.mycourse.services;

import com.mycourse.dao.RoleDao;
import com.mycourse.dao.UserDao;
import com.mycourse.dto.RegisDto;
import com.mycourse.entity.User;
import com.mycourse.entity.UserPassword;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class UserServices {
    @Autowired private UserDao userDao;
    @Autowired private RoleDao roleDao;
    @Autowired private PasswordEncoder passwordEncoder;
    public void registration(RegisDto regisDto){

        User newUser = new User();
        newUser.setAddress(regisDto.getAddress());
        newUser.setEmail(regisDto.getEmail());
        newUser.setUsername(regisDto.getUsername());
        newUser.setFullName(regisDto.getFullName());
        newUser.setMobilePhone(regisDto.getMobilePhone());

       if (regisDto.getRole() != null){
           newUser.setRole(regisDto.getRole());
       } else {
           newUser.setRole(roleDao.findById("r_member").get());
       }

        UserPassword userPassword = new UserPassword();
        userPassword.setUser(newUser);
        userPassword.setPassword(passwordEncoder.encode(regisDto.getPassword()));
        newUser.setUserPassword(userPassword);
        userDao.save(newUser);

    }

}
