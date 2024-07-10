package com.airlines.lmpairlines.dao;

import com.airlines.lmpairlines.model.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDAO extends JpaRepository<Role,Integer> {
    Role findRoleByName(String name);
}
