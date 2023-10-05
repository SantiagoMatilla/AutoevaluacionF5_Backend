package com.F5aes.controller;

import com.F5aes.model.RoleModel;

import com.F5aes.repository.RoleRepository;

import com.F5aes.service.RoleService;

import com.F5aes.service.request.AssignRoleRequest;
import com.F5aes.service.request.RoleAssignImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class RoleController {
    @Autowired
    private RoleService roleService;
@Autowired
private RoleAssignImpl roleAssign;

    // Save method
    @PostMapping("/saveRole")
    public RoleModel createUser(@RequestBody RoleModel roleModel) {

        return roleService.saveRole(roleModel);
    }

    @GetMapping("/roles")
    public List<RoleModel> getRoles() {
        return roleService.getAllRoles();
    }

    @DeleteMapping("role/{id}")
    public void removeRole(@PathVariable Long id) {

        roleService.deleteRole(id);

    }

    @PutMapping("/updateRole/{id}")
    public ResponseEntity<?> updateRole(@RequestBody RoleModel roleModel, @PathVariable Long id) {
        roleService.editRole(roleModel,id);
    return ResponseEntity.ok("Role updated successfully");
    }
// assign role to the users
@PostMapping("/assign-roles")
public ResponseEntity<?> assignRolesToUser(@RequestBody AssignRoleRequest assignRoleRequest) {
    try {
        roleAssign.assignRolesToUser(assignRoleRequest);
        return ResponseEntity.ok("Roles assigned successfully");

    } catch (RuntimeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error assigning role: " + e.getMessage());
    }
}
}
