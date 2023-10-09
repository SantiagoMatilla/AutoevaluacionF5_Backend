package com.F5aes.controller;

import com.F5aes.Exceptions.ResourceNotFoundExceptions;
import com.F5aes.model.Bootcamp;
import com.F5aes.model.UserModel;
import com.F5aes.service.PrincipalService;
import com.F5aes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private PrincipalService principalService;

	// Save method
	@PostMapping("/saveUser")
	public ResponseEntity<String> createUser(@RequestBody UserModel userModel) {

//		Bootcamp selectedBootcamp =userModel.getBootcampModels();
//		if (selectedBootcamp == null) {
//
//			return ResponseEntity.ok("Bootcamp is required!");
//		}
//		Long bootcampId = selectedBootcamp.getId();
//		if(bootcampId ==null){
//			return ResponseEntity.ok("Bootcamp ID is required!");
//		}
//		userModel.setBootcampModels(selectedBootcamp);
		userService.saveUser(userModel);
		return ResponseEntity.ok("Saved Successfully");

	}

	@GetMapping("/users")
	public List<UserModel> getAllUsers() {
		List<UserModel> users = userService.getUsers();
		for (UserModel user : users) {
			Bootcamp bootcamp = user.getBootcampModels();
			if (bootcamp != null) {

				user.setBootcampName(bootcamp.getName());
			}
		}
		return users;
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
