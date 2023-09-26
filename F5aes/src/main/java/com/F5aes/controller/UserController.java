package com.F5aes.controller;

import com.F5aes.model.UserModel;
import com.F5aes.repository.UserRepository;
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

	@GetMapping("/getUsers")
	public List<UserModel> getAllUsers() {

		return userService.getUsers();
	}

	@DeleteMapping("/{id}")
	public void removeUser(@PathVariable Long id) {

		userService.deleteUser(id);

	}

	@PutMapping("/updateUser/{id}")
	public ResponseEntity<?> updateUser(@RequestBody UserModel userModel, @PathVariable Long id) {
		try {
			Optional<UserModel> existingUser = userRepository.findById(id);

			if (existingUser.isPresent()) {
				UserModel updateUser = existingUser.get();
				updateUser.setFirstName(userModel.getFirstName());
				updateUser.setLastName(userModel.getLastName());
				updateUser.setPhone(userModel.getPhone());
				updateUser.setEmail(userModel.getEmail());
				updateUser.setPassword(userModel.getPassword());
				updateUser.setRepeatPassword(userModel.getRepeatPassword());

				userRepository.save(updateUser);
				return ResponseEntity.ok("user updated!");
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not updated");
		}
	}

}
