package com.F5aes.controller;

import com.F5aes.model.Bootcamp;
import com.F5aes.repository.StackRepository;
import com.F5aes.service.PrincipalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class PrincipalControllerTests {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PrincipalService principalService;
@Mock
private StackRepository stackRepository;
	@BeforeEach
	public void setUp() {

	}

	// <----- get all bootcamp test----->
	@Test
	public void testGetAllBootcamp() throws Exception {

		Bootcamp bootcamp1 = new Bootcamp();
		bootcamp1.setId(1L);
		bootcamp1.setName("Bootcamp 1");

		Bootcamp bootcamp2 = new Bootcamp();
		bootcamp2.setId(2L);
		bootcamp2.setName("Bootcamp 2");

		List<Bootcamp> bootcampList = Arrays.asList(bootcamp1, bootcamp2);

		when(principalService.findAll()).thenReturn(bootcampList);


		mockMvc.perform(get("/api/bootcamp"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].id").value(1))
				.andExpect(jsonPath("$[0].name").value("Bootcamp 1"))
				.andExpect(jsonPath("$[1].id").value(2))
				.andExpect(jsonPath("$[1].name").value("Bootcamp 2"));

		verify(principalService, times(1)).findAll();
		verifyNoMoreInteractions(principalService);
	}
	// <----- get bootcamp by id test ----->
	@Test
	public void testGetByIdBootcamp() throws Exception {

		Bootcamp bootcamp = new Bootcamp();
		bootcamp.setId(1L);
		bootcamp.setName("Bootcamp");


		when(principalService.getBootcampById(eq(1L))).thenReturn(bootcamp);

// Act and Assert
		mockMvc.perform(get("/api/bootcamp/1"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.name").value("Bootcamp"));

		verify(principalService, times(1)).getBootcampById(1L);
		verifyNoMoreInteractions(principalService);
	}
// <----- save bootcamp test ----->
	@Test
	public void testCreateBootcamp() throws Exception {

		Bootcamp bootcamp = new Bootcamp();
		bootcamp.setId(1L);
		bootcamp.setName("New Bootcamp");

		when(principalService.saveBootcamp(any(Bootcamp.class))).thenReturn(bootcamp);


		mockMvc.perform(post("/api/saveBootcamp")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"name\":\"New Bootcamp\"}"))
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.name").value("New Bootcamp"));

		verify(principalService, times(1)).saveBootcamp(any(Bootcamp.class));
		verifyNoMoreInteractions(principalService);
	}
	// <----- update bootcamp test ----->

	@Test
	public void  testUpdateBootcamp() throws  Exception{

		Bootcamp updatedBootcamp =  new Bootcamp();
		updatedBootcamp.setId(1L);
		updatedBootcamp.setName("Updated Bootcamp");

		when(principalService.updateBootcamp(eq(1L),any(Bootcamp.class))).thenReturn(updatedBootcamp);

		// Act and Assert

	 mockMvc.perform(put("/api/updateBootcamp/1")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"name\":\"Updated Bootcamp\"}"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.name").value("Updated Bootcamp"));

		verify(principalService, times(1)).updateBootcamp(eq(1L),any(Bootcamp.class));
		verifyNoMoreInteractions(principalService);

	}
	// <----- delete bootcamp test ----->
	@Test
	public void testDeleteBootcamp() throws  Exception{
Long bootcampIdToDelete = 1L;

//Act and Assert
		mockMvc.perform(delete("/api/deleteBootcamp/1"))
				.andExpect(status().isOk())
				.andExpect(content().string("Bootcamp deleted successfully"));

		verify(principalService, times(1)).deleteById(eq(bootcampIdToDelete));
		verifyNoMoreInteractions(principalService);

	}


}
