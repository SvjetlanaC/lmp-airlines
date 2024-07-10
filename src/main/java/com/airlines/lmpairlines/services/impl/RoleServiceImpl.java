package com.airlines.lmpairlines.services.impl;

import com.airlines.lmpairlines.dao.RoleDAO;
import com.airlines.lmpairlines.model.entities.Role;
import com.airlines.lmpairlines.services.RoleService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDAO roleRepository;
    @Override
    public Role findRoleByName(String name) {
        return roleRepository.findRoleByName(name);
    }
}
