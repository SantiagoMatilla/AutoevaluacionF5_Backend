//package com.F5aes.service;
//
//
//import com.F5aes.model.UserModel;
//import com.F5aes.repository.UserRepository;
//import jakarta.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.security.core.userdetails.UserDetails;
////import org.springframework.security.core.userdetails.UserDetailsService;
////import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class UserService implements UserDetailsService {
//	@Autowired
//	private UserRepository userRepository;
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		return null;
//	}
//	public UserModel saveUser(UserModel userModel) {
//
//	return 	userRepository.save(userModel);
//	}
//
//
//
//	public List<UserModel> getUsers() {
//
//		return userRepository.findAll();
//	}
//	public UserModel getUserById(Long id) {
//		Optional<UserModel> optionalUser = userRepository.findById(id);
//		return optionalUser.orElse(null);
//	}
//
//@Transactional
//	public UserModel editUser(Long id,UserModel userDetails ) {
//				UserModel user = getUserById(id);
//
//				user.setFirstName(userDetails.getFirstName());
//				user.setLastName(userDetails.getLastName());
//				user.setPhone(userDetails.getPhone());
//				user.setEmail(userDetails.getEmail());
//				user.setPassword(userDetails.getPassword());
//				user.setRepeatPassword(userDetails.getRepeatPassword());
//
//				return saveUser(user);
//
//	}
//	public void deleteUser (Long id){
//
//		userRepository.deleteById(id);
//	}
//
//
//}
