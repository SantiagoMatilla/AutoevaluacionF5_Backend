package com.F5aes.controller;

import com.F5aes.model.CoderEvaluation;
import com.F5aes.model.Content;
import com.F5aes.model.Skill;
import com.F5aes.model.UserModel;
import com.F5aes.repository.ContentRepository;
import com.F5aes.repository.UserRepository;
import com.F5aes.service.CoderEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CoderEvaluationController {

	@Autowired
	private CoderEvaluationService coderEvaluationService;
	@Autowired
	private ContentRepository contentRepository;
	@Autowired
	private UserRepository userRepository;

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

	@PostMapping("/evaluation")
	public ResponseEntity<Map<String,Object>> doEvaluation(@RequestBody CoderEvaluation coderEvaluation) {

		Content content= null;
		//UserModel userModel = null;
		if(coderEvaluation.getContent()  !=null) {
			content = contentRepository.findById(coderEvaluation.getContent().getId()).orElse(null);
		}
	/*	if(coderEvaluation.getCoder()  !=null) {
			userModel = userRepository.findById(coderEvaluation.getCoder().getId()).orElse(null);
		}
		if(coderEvaluation.getEvaluator()  !=null) {
			userModel = userRepository.findById(coderEvaluation.getEvaluator().getId()).orElse(null);
		} */
		if (content== null) {
			CoderEvaluation evaluation = new CoderEvaluation();

			evaluation.setFirstEvaluation(coderEvaluation.isFirstEvaluation());
			evaluation.setSecondEvaluation(coderEvaluation.isSecondEvaluation());
			evaluation.setThirdEvaluation(coderEvaluation.isThirdEvaluation());
			evaluation.setFourthEvaluation(coderEvaluation.isFourthEvaluation());

			evaluation.setFifthCriteria(coderEvaluation.isFifthCriteria());
			evaluation.setSecondEvaluation(coderEvaluation.isSecondEvaluation());
			evaluation.setThirdCriteria(coderEvaluation.isThirdCriteria());
			evaluation.setFourthCriteria(coderEvaluation.isFourthCriteria());
			evaluation.setFifthCriteria(coderEvaluation.isFifthCriteria());
			evaluation.setSixthCriteria(coderEvaluation.isSixthCriteria());
			evaluation.setSeventhCriteria(coderEvaluation.isSeventhCriteria());
			CoderEvaluation result=	coderEvaluationService.createCoderEvaluation(evaluation);
			Map<String, Object> response = new HashMap<>();
			response.put("message", "Content not found or not specified");
			response.put("data", result);
			return ResponseEntity.ok(response);

		}
		CoderEvaluation evaluation = new CoderEvaluation();
		evaluation.setContent(coderEvaluation.getContent());

		evaluation.setFirstEvaluation(coderEvaluation.isFirstEvaluation());
		evaluation.setSecondEvaluation(coderEvaluation.isSecondEvaluation());
		evaluation.setThirdEvaluation(coderEvaluation.isThirdEvaluation());
		evaluation.setFourthEvaluation(coderEvaluation.isFourthEvaluation());

		evaluation.setFifthCriteria(coderEvaluation.isFifthCriteria());
		evaluation.setSecondEvaluation(coderEvaluation.isSecondEvaluation());
		evaluation.setThirdCriteria(coderEvaluation.isThirdCriteria());
		evaluation.setFourthCriteria(coderEvaluation.isFourthCriteria());
		evaluation.setFifthCriteria(coderEvaluation.isFifthCriteria());
		evaluation.setSixthCriteria(coderEvaluation.isSixthCriteria());
		evaluation.setSeventhCriteria(coderEvaluation.isSeventhCriteria());
		CoderEvaluation result=	coderEvaluationService.createCoderEvaluation(evaluation);
		Map<String, Object> response = new HashMap<>();
		response.put("message", "successfully saved!");
		response.put("data", result);
		return ResponseEntity.ok(response);

	}

	@PutMapping("/evaluation/{id}")
	public ResponseEntity<CoderEvaluation> updateCriteriaState(
			@PathVariable Long id,
			@RequestParam String criteriaName,
			@RequestParam Boolean newState
	) {
		CoderEvaluation evaluation = coderEvaluationService.getCoderEvaluationById(id);

		if (evaluation != null) {
			switch (criteriaName) {
				case "firstCriteria" -> evaluation.setFirstCriteria(newState);
				case "secondCriteria" -> evaluation.setSecondCriteria(newState);
				case "thirdCriteria" -> evaluation.setThirdEvaluation(newState);
				case "fourthCriteria" -> evaluation.setFourthEvaluation(newState);
				case "fifthCriteria" -> evaluation.setFifthCriteria(newState);
				case "sixthCriteria" -> evaluation.setSixthCriteria(newState);
				case "seventhCriteria" -> evaluation.setSeventhCriteria(newState);
				default -> {
				}
			}

			CoderEvaluation updatedEvaluation = coderEvaluationService.updateCoderEvaluation(id, evaluation);
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
