package com.F5aes.controller;

import com.F5aes.model.Role;

import com.F5aes.service.RoleService;

import com.F5aes.service.request.AssignRoleRequest;
import com.F5aes.service.request.RoleAssignImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Role createUser(@RequestBody Role role) {

        return roleService.saveRole(role);
    }

    @GetMapping("/roles")
    public List<Role> getRoles() {
        return roleService.getAllRoles();
    }

    @DeleteMapping("role/{id}")
    public void removeRole(@PathVariable Long id) {

        roleService.deleteRole(id);

    }

    @PutMapping("/updateRole/{id}")
    public ResponseEntity<?> updateRole(@RequestBody Role role, @PathVariable Long id) {
        roleService.editRole(role,id);
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
