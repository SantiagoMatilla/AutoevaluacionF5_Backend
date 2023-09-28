package com.F5aes.service;

import com.F5aes.model.RoleModel;

import com.F5aes.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public RoleModel saveRole(RoleModel roleModel) {

        return roleRepository.save(roleModel);
    }

    public List<RoleModel> getRole() {

        return roleRepository.findAll();
    }

    public void deleteRole(Long id) {

        roleRepository.deleteById(id);
    }
}
