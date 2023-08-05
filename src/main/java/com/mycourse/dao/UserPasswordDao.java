package com.mycourse.dao;

import com.mycourse.entity.UserPassword;
import org.springframework.data.repository.CrudRepository;

public interface UserPasswordDao extends CrudRepository<UserPassword, String> {
}
