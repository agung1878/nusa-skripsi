package com.mycourse.dao;

import com.mycourse.entity.Permission;
import org.springframework.data.repository.CrudRepository;

public interface PermissionDao extends CrudRepository<Permission, String> {
}
