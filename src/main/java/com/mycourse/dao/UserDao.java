package com.mycourse.dao;

import com.mycourse.entity.Role;
import com.mycourse.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface UserDao extends CrudRepository<User, String>, PagingAndSortingRepository<User, String> {

    List<User> findAllByRole(Role role);
    Optional<User> findByUsername(String username);

}
