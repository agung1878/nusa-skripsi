package com.mycourse.dao;

import com.mycourse.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserDao extends CrudRepository<User, String>, PagingAndSortingRepository<User, String> {

}
