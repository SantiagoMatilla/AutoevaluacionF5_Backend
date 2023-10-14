package com.F5aes.controller;

import com.F5aes.model.Bootcamp;
import com.F5aes.model.UserModel;
import com.F5aes.repository.BootcampRepository;
import com.F5aes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	private BootcampRepository bootcampRepository;

	// Save method
	@PostMapping("/saveUser")
	public ResponseEntity<Map<String,Object>> createUser(@RequestBody UserModel userRequest) {

		Bootcamp bootcamp = null;
		// Retrieve the Bootcamp object by its ID
	if(userRequest.getBootcamp() !=null) {
		 bootcamp = bootcampRepository.findById(userRequest.getBootcamp().getId()).orElse(null);
	}
		if (bootcamp == null) {
			UserModel user = new UserModel();
			user.setFirstName(userRequest.getFirstName());
			user.setLastName(userRequest.getLastName());
			user.setEmail(userRequest.getEmail());
			user.setPhone(userRequest.getPhone());
			user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
			user.setRepeatPassword(passwordEncoder.encode(userRequest.getRepeatPassword()));
			UserModel result=	userService.saveUser(user);
			Map<String, Object> response = new HashMap<>();
			response.put("message", "Bootcamp not found");
			response.put("data", result);
			return ResponseEntity.ok(response);
		}
		UserModel user = new UserModel();
		user.setFirstName(userRequest.getFirstName());
		user.setLastName(userRequest.getLastName());
		user.setEmail(userRequest.getEmail());
		user.setPhone(userRequest.getPhone());
		user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
		user.setRepeatPassword(passwordEncoder.encode(userRequest.getRepeatPassword()));
		user.setBootcamp(bootcamp);

		UserModel result=	userService.saveUser(user);
		Map<String, Object> response = new HashMap<>();
		response.put("message", "Saved Successfully");
		response.put("data", result);
		return ResponseEntity.ok(response);

	}

	@GetMapping("/users")
	public List<UserModel> getAllUsers() {
		return userService.getUsers();

	}

	@GetMapping("/user/{id}")
	public UserModel getUserById(@PathVariable Long id){
		return  userService.getUserById(id);
	}
	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<String> removeUser(@PathVariable Long id) {

	 	userService.deleteUser(id);
		 return ResponseEntity.ok("User deleted successfully");
	}

	@PutMapping("/updateUser/{id}")
	public UserModel updateUser(@PathVariable Long id ,@RequestBody UserModel userModel) {
	return 	userService.editUser(id,userModel);

	}

}
