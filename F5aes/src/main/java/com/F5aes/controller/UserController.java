package com.F5aes.controller;

import com.F5aes.model.Bootcamp;
import com.F5aes.model.UserModel;
import com.F5aes.service.PrincipalService;
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
	private PrincipalService principalService;

	// Save method
	@PostMapping("/saveUser")
	public ResponseEntity<?> createUser(@RequestBody UserModel userModel) {
		Bootcamp selectedBootcamp =principalService.getBootcampById(userModel.getBootcampModels().getId());
		if (selectedBootcamp == null) {

			return  ResponseEntity.badRequest().body("Select bootcamp not found");

		}
		userModel.setBootcampModels(selectedBootcamp);
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
	@DeleteMapping("/user/{id}")
	public void removeUser(@PathVariable Long id) {

		userService.deleteUser(id);

	}

	@PutMapping("/updateUser/{id}")
	public ResponseEntity<?> updateUser(@RequestBody UserModel userModel, @PathVariable Long id) {
		userService.editUser(userModel, id);
		return ResponseEntity.ok("successfully updated!");
	}

}
