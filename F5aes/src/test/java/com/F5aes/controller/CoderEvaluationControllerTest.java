package com.F5aes.controller;

import com.F5aes.model.CoderEvaluation;
import com.F5aes.model.Content;
import com.F5aes.model.UserModel;
import com.F5aes.service.CoderEvaluationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class CoderEvaluationControllerTest {

	@InjectMocks
	private CoderEvaluationController coderEvaluationController;

	@Mock
	private CoderEvaluationService coderEvaluationService;

	@BeforeEach
	public void setup() {

	}


	@Test
	public void testGetAllUserEvaluations() {
		CoderEvaluation evaluation = new CoderEvaluation(1L, new UserModel(), new Content(), new UserModel(),
				true, true, false, false,
				false, false, false, false, false,
				false, false, 0.0);
		Mockito.when(coderEvaluationService.createCoderEvaluation(evaluation)).thenReturn(evaluation);
		ResponseEntity<CoderEvaluation> response = coderEvaluationController.createUserEvaluation(evaluation);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(evaluation, response.getBody());
	}
	@Test
	public void testGetUserEvaluationById() {
		Long id = 1L;
		CoderEvaluation evaluation = new CoderEvaluation(id, new UserModel(), new Content(), new UserModel(),
				true, true, false, false,
				true, false, true, false,
				false, false, false,0.0);
		Mockito.when(coderEvaluationService.getCoderEvaluationById(id)).thenReturn(evaluation);

		ResponseEntity<CoderEvaluation> response = coderEvaluationController.getUserEvaluationById(id);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(evaluation, response.getBody());
	}
	@Test
	public void testGetUserEvaluationByIdNotFound() {
		Long id = 1L;
		Mockito.when(coderEvaluationService.getCoderEvaluationById(id)).thenReturn(null);

		ResponseEntity<CoderEvaluation> response = coderEvaluationController.getUserEvaluationById(id);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	@Test
	public void testCreateUserEvaluation() {
		CoderEvaluation evaluation = new CoderEvaluation(1L, new UserModel(), new Content(), new UserModel(),
				true, true, false, false,
				true, false, true, false,
				false, false, false,0.0);
		Mockito.when(coderEvaluationService.createCoderEvaluation(evaluation)).thenReturn(evaluation);

		ResponseEntity<CoderEvaluation> response = coderEvaluationController.createUserEvaluation(evaluation);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(evaluation, response.getBody());
	}
	@Test
	public void testUpdateUserEvaluation() {
		Long id = 1L;
		CoderEvaluation existingEvaluation = new CoderEvaluation(id, new UserModel(), new Content(), new UserModel(),
				true, true, false, false,
				true, false, true, false,
				false, false, false,0.0);
		CoderEvaluation updatedEvaluation = new CoderEvaluation(id, new UserModel(), new Content(), new UserModel(),
				false, true, true, false,
				false, true, false, true,
				false, false,false,0.0);

		Mockito.when(coderEvaluationService.updateCoderEvaluation(id, updatedEvaluation)).thenReturn(updatedEvaluation);


		ResponseEntity<CoderEvaluation> response = coderEvaluationController.updateUserEvaluation(id, updatedEvaluation);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(updatedEvaluation, response.getBody());
	}
	@Test
	public void testDeleteUserEvaluation() {
		Long id = 1L;
		ResponseEntity<Void> response = coderEvaluationController.deleteUserEvaluation(id);
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}
}
