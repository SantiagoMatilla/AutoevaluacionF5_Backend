package com.F5aes.controller;


import com.F5aes.model.Role;
import com.F5aes.service.RoleService;

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
public class RoleControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private RoleService roleService;

	@BeforeEach
	public void setUp() {

	}

	//	<----- get all roles ----->
	@Test
	public void testGetAllRoles() throws Exception {

		Role role1 = new Role();
		role1.setId(1L);
		role1.setName("Admin");

		Role role2 = new Role();
		role2.setId(2L);
		role2.setName("User");

		List<Role> roles = Arrays.asList(role1, role2);

		when(roleService.getAllRoles()).thenReturn(roles);

		mockMvc.perform(get("/api/roles"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].id").value(1))
				.andExpect(jsonPath("$[0].name").value("Admin"))
				.andExpect(jsonPath("$[1].id").value(2))
				.andExpect(jsonPath("$[1].name").value("User"));

		verify(roleService, times(1)).getAllRoles();
		verifyNoMoreInteractions(roleService);
	}


	// <----- role create test ----->
	@Test
	public void testCreateRole() throws Exception {

		Role role = new Role();
		role.setId(1L);
		role.setName("NewRole");


		when(roleService.saveRole(any(Role.class))).thenReturn(role);


		mockMvc.perform(post("/api/saveRole")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"name\":\"NewRole\"}"))
				.andExpect(status().isOk())
				.andExpect(content().string("Saved Successfully"));

		verify(roleService, times(1)).saveRole(any(Role.class));
		verifyNoMoreInteractions(roleService);
	}
	// <----- update role test ----->

	@Test
	public void  testUpdateRole() throws  Exception{

		Role updatedRole =  new Role();
		updatedRole.setId(1L);
		updatedRole.setName("Updated Role");

		when(roleService.editRole(eq(1L),any(Role.class))).thenReturn(updatedRole);

		// Act and Assert

		mockMvc.perform(put("/api/updateRole/1")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"name\":\"Updated Role\"}"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.name").value("Updated Role"));

		verify(roleService, times(1)).editRole(eq(1L),any(Role.class));
		verifyNoMoreInteractions(roleService);

	}
	// <----- delete role test ----->
	@Test
	public void testDeleteRole() throws  Exception{
		Long roleIdToDelete = 1L;

//Act and Assert
		mockMvc.perform(delete("/api/deleteRole/1"))
				.andExpect(status().isOk());

		verify(roleService, times(1)).deleteRole(eq(roleIdToDelete));
		verifyNoMoreInteractions(roleService);

	}

}
