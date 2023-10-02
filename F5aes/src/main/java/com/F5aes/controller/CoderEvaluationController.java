package com.F5aes.controller;

import com.F5aes.model.CoderEvaluationModel;
import com.F5aes.service.CoderEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/evaluations")
public class CoderEvaluationController {

	@Autowired
	private CoderEvaluationService coderEvaluationService;

	@GetMapping
	public ResponseEntity<List<CoderEvaluationModel>> getAllUserEvaluations() {
		List<CoderEvaluationModel> evaluations = coderEvaluationService.getAllCoderEvaluations();
		return new ResponseEntity<>(evaluations, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CoderEvaluationModel> getUserEvaluationById(@PathVariable Long id) {
		CoderEvaluationModel evaluation = coderEvaluationService.getCoderEvaluationById(id);

		if (evaluation != null) {
			return new ResponseEntity<>(evaluation, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<CoderEvaluationModel> createUserEvaluation(@RequestBody CoderEvaluationModel coderEvaluation) {
		CoderEvaluationModel createdEvaluation = coderEvaluationService.createCoderEvaluation(coderEvaluation);
		return new ResponseEntity<>(createdEvaluation, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CoderEvaluationModel> updateUserEvaluation(@PathVariable Long id,
	                                              @RequestBody CoderEvaluationModel updatedCoderEvaluation) {
		CoderEvaluationModel updatedEvaluation = coderEvaluationService.updateCoderEvaluation(id, updatedCoderEvaluation);

		if (updatedEvaluation != null) {
			return new ResponseEntity<>(updatedEvaluation, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUserEvaluation(@PathVariable Long id) {
		coderEvaluationService.deleteCoderEvaluation(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
