package com.F5aes.controller;

import com.F5aes.model.RoleModel;
import com.F5aes.model.UserModel;
import com.F5aes.repository.RoleRepository;
import com.F5aes.repository.UserRepository;
import com.F5aes.service.RoleService;
import com.F5aes.service.UserService;
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
    private RoleRepository roleRepository;

    //Save method
    @PostMapping("/saveRole")
    public RoleModel createUser(@RequestBody RoleModel roleModel){

        return roleService.saveRole(roleModel);
    }
    @GetMapping("/getRole")
    public List<RoleModel> getAllRoles(){

        return  roleService.getRole();
    }
    @DeleteMapping("/{id}")
    public void removeRole(@PathVariable Long id){

        roleService.deleteRole(id);

    }
    @PutMapping("/updateRole/{id}")
    public ResponseEntity<?> updateRole(@RequestBody RoleModel roleModel, @PathVariable Long id) {
        try{
            Optional<RoleModel> existingRole = roleRepository.findById(id);

            if (existingRole.isPresent()) {
                RoleModel updateRole =existingRole.get();
                updateRole.setId(roleModel.getId());
                updateRole.setName(roleModel.getName());

                roleRepository.save(updateRole);
                return ResponseEntity.ok("role updated!");
            }
            else {
                return ResponseEntity.notFound().build();
            }
        }catch (Exception e){
            e.printStackTrace();
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not updated");
        }
    }

}
