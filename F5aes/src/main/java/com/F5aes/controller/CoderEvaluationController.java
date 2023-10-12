package com.F5aes.controller;

import com.F5aes.model.CoderEvaluation;
import com.F5aes.service.CoderEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CoderEvaluationController {

	@Autowired
	private CoderEvaluationService coderEvaluationService;

	@GetMapping("/evaluations")
	public ResponseEntity<List<CoderEvaluation>> getAllUserEvaluations() {
		List<CoderEvaluation> evaluations = coderEvaluationService.getAllCoderEvaluations();
		return new ResponseEntity<>(evaluations, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CoderEvaluation> getUserEvaluationById(@PathVariable Long id) {
		CoderEvaluation evaluation = coderEvaluationService.getCoderEvaluationById(id);

		if (evaluation != null) {
			return new ResponseEntity<>(evaluation, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/saveEvaluation")
	public ResponseEntity<CoderEvaluation> createUserEvaluation(@RequestBody CoderEvaluation coderEvaluation) {
		CoderEvaluation createdEvaluation = coderEvaluationService.createCoderEvaluation(coderEvaluation);
		return new ResponseEntity<>(createdEvaluation, HttpStatus.CREATED);
	}

	@PutMapping("/updateEvaluation/{id}")
	public ResponseEntity<CoderEvaluation> updateUserEvaluation(@PathVariable Long id,
	                                                            @RequestBody CoderEvaluation updatedCoderEvaluation) {
		CoderEvaluation updatedEvaluation = coderEvaluationService.updateCoderEvaluation(id, updatedCoderEvaluation);

		if (updatedEvaluation != null) {
			return new ResponseEntity<>(updatedEvaluation, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("deleteEvaluation/{id}")
	public ResponseEntity<Void> deleteUserEvaluation(@PathVariable Long id) {
		coderEvaluationService.deleteCoderEvaluation(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
