package com.F5aes.service;

import com.F5aes.model.UserModel;
import com.F5aes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public UserModel saveUser(UserModel userModel){

		return  userRepository.save(userModel);
	}
//	public UserModel editUser( Long id,UserModel usermodel){
//		UserModel usermodel = userRepository.findById(id);
//		userModel.setEmail(userModel.getEmail());
//		userModel.setFirstName(userModel.getFirstName());
//		userModel.setLastName(userModel.getLastName());
//		userModel.setPhone(userModel.getPhone());
//		userModel.setPassword(userModel.getPassword());
//		userModel.setRepeatPassword(userModel.getRepeatPassword());
//		return  userRepository.save(userModel);
//	}

	public List<UserModel> getUsers(){

		return userRepository.findAll();
	}

	public void deleteUser(Long id){

		userRepository.deleteById(id);
	}
}
