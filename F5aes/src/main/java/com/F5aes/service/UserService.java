package com.F5aes.service;

import com.F5aes.model.UserModel;
import com.F5aes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public UserModel saveUser(UserModel userModel) {

		return userRepository.save(userModel);
	}


	public List<UserModel> getUsers() {

		return userRepository.findAll();
	}
	public void editUser(@RequestBody UserModel userModel, @PathVariable Long id) {
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
				ResponseEntity.ok("user updated!");
			} else {
				ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not updated");
		}
	}
	public void deleteUser (Long id){

		userRepository.deleteById(id);
	}

}
