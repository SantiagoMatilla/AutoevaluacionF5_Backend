package com.F5aes.service;

import com.F5aes.model.RoleModel;

import com.F5aes.repository.RoleRepository;

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

    public RoleModel saveRole(RoleModel roleModel) {

        return roleRepository.save(roleModel);
    }

    public List<RoleModel> getAllRoles() {

        return roleRepository.findAll();
    }
    public void editRole(@RequestBody RoleModel roleModel, @PathVariable Long id) {
        try {
            Optional<RoleModel> existingRole = roleRepository.findById(id);

            if (existingRole.isPresent()) {
                RoleModel updateRole = existingRole.get();
                updateRole.setId(roleModel.getId());
                updateRole.setName(roleModel.getName());

                roleRepository.save(updateRole);
                 ResponseEntity.ok("role updated!");
            } else {
                 ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
             ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not updated");
        }
    }
    public void deleteRole(Long id) {

        roleRepository.deleteById(id);
    }
}
