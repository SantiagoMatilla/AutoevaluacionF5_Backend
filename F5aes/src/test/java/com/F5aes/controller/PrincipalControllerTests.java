package com.F5aes.controller;

import com.F5aes.model.Bootcamp;
import com.F5aes.model.Skill;
import com.F5aes.model.Stack;
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

		// <--- stack ---->


// <----- get all stack test----->
@Test
public void testGetAllStack() throws Exception {

	Stack stack1 = new Stack();
	stack1.setId(1L);
	stack1.setName("Stack 1");

	Stack stack2 = new Stack();
	stack2.setId(2L);
	stack2.setName("Stack 2");

	List<Stack> stackList = Arrays.asList(stack1, stack2);

	when(principalService.getAllStack()).thenReturn(stackList);


	mockMvc.perform(get("/api/stacks"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$[0].id").value(1))
			.andExpect(jsonPath("$[0].name").value("Stack 1"))
			.andExpect(jsonPath("$[1].id").value(2))
			.andExpect(jsonPath("$[1].name").value("Stack 2"));

	verify(principalService, times(1)).getAllStack();
	verifyNoMoreInteractions(principalService);
}
	// <----- get stack by id test ----->
	@Test
	public void testGetByIdStack() throws Exception {

		Stack stack = new Stack();
		stack.setId(1L);
		stack.setName("Stack");


		when(principalService.getStackById(eq(1L))).thenReturn(stack);

// Act and Assert
		mockMvc.perform(get("/api/stack/1"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.name").value("Stack"));

		verify(principalService, times(1)).getStackById(1L);
		verifyNoMoreInteractions(principalService);
	}
	// <----- save stack test ----->
//	@Test
//	public void testCreateStack() throws Exception {
//
//		Stack stack = new Stack();
//		stack.setId(1L);
//		stack.setName("New Stack");
//
//		when(principalService.createStack(any(Stack.class))).thenReturn(stack);
//
//
//		mockMvc.perform(post("/api/saveStack")
//						.contentType(MediaType.APPLICATION_JSON)
//						.content("{\"name\":\"New Stack\"}"))
//				.andExpect(status().isCreated())
//				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
//				.andExpect(jsonPath("$.id").value(1))
//				.andExpect(jsonPath("$.name").value("New Stack"));
//
//		verify(principalService, times(1)).createStack(any(Stack.class));
//		verifyNoMoreInteractions(principalService);
//	}
//	// <----- update stack test ----->
//	@Test
//	public void  testUpdateStack() throws  Exception{
//
//		Stack updateStack =  new Stack();
//		updateStack.setId(1L);
//		updateStack.setName("Updated Stack");
//
//		when(principalService.editStack(eq(1L),any(Stack.class))).thenReturn(updateStack);
//
//		// Act and Assert
//
//		mockMvc.perform(put("/api/updateStack/1")
//						.contentType(MediaType.APPLICATION_JSON)
//						.content("{\"name\":\"Updated Stack\"}"))
//				.andExpect(status().isOk())
//				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
//				.andExpect(jsonPath("$.id").value(1))
//				.andExpect(jsonPath("$.name").value("Updated Stack"));
//
//		verify(principalService, times(1)).editStack(eq(1L),any(Stack.class));
//		verifyNoMoreInteractions(principalService);
//
//	}
//	// <----- delete stack test ----->
//	@Test
//	public void testDeleteStack() throws  Exception{
//		Long stackIdToDelete = 1L;
//
////Act and Assert
//		mockMvc.perform(delete("/api/deleteStack/1"))
//				.andExpect(status().isOk())
//				.andExpect(content().string("Stack deleted successfully"));
//
//		verify(principalService, times(1)).deleteStack(eq(stackIdToDelete));
//		verifyNoMoreInteractions(principalService);
//
//	}

	// <--- skill ---->


	// <----- get all skill test----->
	@Test
	public void testGetAllSkill() throws Exception {

		Skill skill1 = new Skill();
		skill1.setId(1L);
		skill1.setName("Skill 1");

		Skill skill2 = new Skill();
		skill2.setId(2L);
		skill2.setName("Skill 2");

		List<Skill> skills = Arrays.asList(skill1, skill2);

		when(principalService.getAllSkill()).thenReturn(skills);


		mockMvc.perform(get("/api/skills"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].id").value(1))
				.andExpect(jsonPath("$[0].name").value("Skill 1"))
				.andExpect(jsonPath("$[1].id").value(2))
				.andExpect(jsonPath("$[1].name").value("Skill 2"));

		verify(principalService, times(1)).getAllSkill();
		verifyNoMoreInteractions(principalService);
	}
	// <----- get skill by id test ----->
	@Test
	public void testGetByIdSkill() throws Exception {

		Skill skill = new Skill();
		skill.setId(1L);
		skill.setName("Skill");


		when(principalService.getSkillById(eq(1L))).thenReturn(skill);

// Act and Assert
		mockMvc.perform(get("/api/skill/1"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.name").value("Skill"));

		verify(principalService, times(1)).getSkillById(1L);
		verifyNoMoreInteractions(principalService);
	}
	// <----- save skill test ----->
	@Test
	public void testCreateSkill() throws Exception {

		Skill skill = new Skill();
		skill.setId(1L);
		skill.setName("New Skill");

		when(principalService.createSkill(any(Skill.class))).thenReturn(skill);


		mockMvc.perform(post("/api/saveSkill")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"name\":\"New Skill\"}"))
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.name").value("New Skill"));

		verify(principalService, times(1)).createSkill(any(Skill.class));
		verifyNoMoreInteractions(principalService);
	}
	// <----- update skill test ----->
	@Test
	public void  testUpdateSkill() throws  Exception{

		Skill updateSkill =  new Skill();
		updateSkill.setId(1L);
		updateSkill.setName("Updated Skill");

		when(principalService.editSkill(eq(1L),any(Skill.class))).thenReturn(updateSkill);

		// Act and Assert

		mockMvc.perform(put("/api/updateSkill/1")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"name\":\"Updated Skill\"}"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.name").value("Updated Skill"));

		verify(principalService, times(1)).editSkill(eq(1L),any(Skill.class));
		verifyNoMoreInteractions(principalService);

	}
	// <----- delete skill test ----->
	@Test
	public void testDeleteSkill() throws  Exception{
		Long skillIdToDelete = 1L;

//Act and Assert
		mockMvc.perform(delete("/api/deleteSkill/1"))
				.andExpect(status().isOk())
				.andExpect(content().string("Skill deleted successfully!"));

		verify(principalService, times(1)).deleteSkill(eq(skillIdToDelete));
		verifyNoMoreInteractions(principalService);

	}
}
