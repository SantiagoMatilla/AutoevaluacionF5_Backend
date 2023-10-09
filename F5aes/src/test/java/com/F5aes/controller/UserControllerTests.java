package com.F5aes.controller;

import com.F5aes.model.Bootcamp;
import com.F5aes.model.UserModel;
import com.F5aes.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class UserControllerTests {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@BeforeEach
	public void setUp() {

	}

//	<----- get all users ----->
	@Test
	public void testGetAllUsers() throws Exception {

		UserModel user = new UserModel();
		user.setId(1L);
		user.setFirstName("User1");

		UserModel user2 = new UserModel();
		user2.setId(2L);
		user2.setFirstName("User2");

		List<UserModel> userModelList = Arrays.asList(user, user2);

		when(userService.getUsers()).thenReturn(userModelList);

		mockMvc.perform(get("/api/users"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].id").value(1))
				.andExpect(jsonPath("$[0].firstName").value("User1"))
				.andExpect(jsonPath("$[1].id").value(2))
				.andExpect(jsonPath("$[1].firstName").value("User2"));

		verify(userService, times(1)).getUsers();
		verifyNoMoreInteractions(userService);
	}
//	<----- get user by Id ----->
@Test
public void testGetByIdUser() throws Exception {

	UserModel user = new UserModel();
	user.setId(1L);
	user.setFirstName("User");

	when(userService.getUserById(eq(1L))).thenReturn((user));

// <----- Act and Assert ----->

	mockMvc.perform(get("/api/user/1"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.id").value(1))
			.andExpect(jsonPath("$.firstName").value("User"));

	verify(userService, times(1)).getUserById(1L);
	verifyNoMoreInteractions(userService);
}
	// <----- user registration test ----->
	@Test
	public void testUserRegistration() throws Exception {

		UserModel user = new UserModel();
		user.setId(1L);
		user.setFirstName("NewUser");
		Bootcamp bootcamp = new Bootcamp();
		bootcamp.setId(1L);
		user.setBootcampModels(bootcamp);

		when(userService.saveUser(any(UserModel.class))).thenReturn(user);


		mockMvc.perform(post("/api/saveUser")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"firstName\":\"NewUser\"}"))
				.andExpect(status().isOk())
				.andExpect(content().string("Saved Successfully"));

		verify(userService, times(1)).saveUser(any(UserModel.class));
		verifyNoMoreInteractions(userService);
	}
	// <----- update user test ----->

	@Test
	public void  testUpdateUser() throws  Exception{

		UserModel updatedUser =  new UserModel();
		updatedUser.setId(1L);
		updatedUser.setFirstName("Updated User");

		when(userService.editUser(eq(1L),any(UserModel.class))).thenReturn(updatedUser);

		// Act and Assert

		mockMvc.perform(put("/api/updateUser/1")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"firstName\":\"Updated User\"}"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.firstName").value("Updated User"));

		verify(userService, times(1)).editUser(eq(1L),any(UserModel.class));
		verifyNoMoreInteractions(userService);

	}
	// <----- delete user test ----->
	@Test
	public void testDeleteUser() throws  Exception{
		Long userIdToDelete = 1L;

//Act and Assert
		mockMvc.perform(delete("/api/deleteUser/1"))
				.andExpect(status().isOk())
				.andExpect(content().string("User deleted successfully"));

		verify(userService, times(1)).deleteUser(eq(userIdToDelete));
		verifyNoMoreInteractions(userService);

	}

}
