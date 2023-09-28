package com.F5aes.controller;

import com.F5aes.model.UserModel;
import com.F5aes.repository.UserRepository;
import com.F5aes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;

	// Save method
	@PostMapping("/saveUser")
	public UserModel createUser(@RequestBody UserModel userModel) {

		return userService.saveUser(userModel);
	}

	@GetMapping("/users")
	public List<UserModel> getAllUsers() {

		return userService.getUsers();
	}

	@DeleteMapping("/user/{id}")
	public void removeUser(@PathVariable Long id) {

		userService.deleteUser(id);

	}

	@PutMapping("/updateUser/{id}")
	public ResponseEntity<?> updateUser(@RequestBody UserModel userModel,@PathVariable Long id) {
		userService.editUser(userModel,id);
		return ResponseEntity.ok("successfully updated!");
	}

}
