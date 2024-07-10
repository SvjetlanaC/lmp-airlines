package com.airlines.lmpairlines.services;

import com.airlines.lmpairlines.model.entities.Role;

public interface RoleService {
    Role findRoleByName(String name);
}
