package com.mycourse.dao;

import com.mycourse.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoleDao extends CrudRepository<Role, String>, PagingAndSortingRepository<Role, String> {
}
