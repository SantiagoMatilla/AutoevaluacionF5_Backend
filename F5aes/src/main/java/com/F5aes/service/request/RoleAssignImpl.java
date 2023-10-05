package com.F5aes.service.request;

import com.F5aes.model.RoleModel;
import com.F5aes.model.UserModel;
import com.F5aes.repository.RoleRepository;
import com.F5aes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleAssignImpl {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	public void assignRolesToUser(AssignRoleRequest assignRoleRequest) {
		Long userId = assignRoleRequest.getUserId();
		Long roleId = assignRoleRequest.getRoleId();

		UserModel user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
		RoleModel role = roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));

		user.addRole(role); // Add the new role to the user

		userRepository.save(user);
	}
}
