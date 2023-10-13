package com.F5aes.service;

import com.F5aes.model.Role;

import com.F5aes.model.UserModel;
import com.F5aes.repository.RoleRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Role saveRole(Role role) {

        return roleRepository.save(role);
    }

    public List<Role> getAllRoles() {

        return roleRepository.findAll();
    }
    public Role getRoleById(Long id) {
        Optional<Role> optionalRole = roleRepository.findById(id);
        return optionalRole.orElse(null);
    }
    @Transactional
    public Role editRole(Long id, Role role ) {
        Role Updatedrole = getRoleById(id);
        Updatedrole.setId(role.getId());
        Updatedrole.setName(role.getName());

        return saveRole(Updatedrole);

    }
    public void deleteRole(Long id) {

        roleRepository.deleteById(id);
    }
}
