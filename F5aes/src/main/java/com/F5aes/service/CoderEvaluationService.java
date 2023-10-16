package com.F5aes.service;

import com.F5aes.model.CoderEvaluation;
import com.F5aes.repository.CoderEvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CoderEvaluationService {

	@Autowired
	private CoderEvaluationRepository coderEvaluationRepository;
// get all
	public List<CoderEvaluation> getAllCoderEvaluations() {
		return coderEvaluationRepository.findAll();
	}
// get by id
	public CoderEvaluation getCoderEvaluationById(Long id) {
		return coderEvaluationRepository.findById(id).orElse(null);
	}
//create method
	public CoderEvaluation createCoderEvaluation(CoderEvaluation userEvaluation) {
		return coderEvaluationRepository.save(userEvaluation);
	}
// update method
	public CoderEvaluation updateCoderEvaluation(Long id, CoderEvaluation updatedUserEvaluation) {
		CoderEvaluation existingUserEvaluation = coderEvaluationRepository.findById(id).orElse(null);

		if (existingUserEvaluation != null) {
			//evaluation phases
			existingUserEvaluation.setFirstEvaluation(updatedUserEvaluation.isFirstEvaluation());
			existingUserEvaluation.setSecondEvaluation(updatedUserEvaluation.isSecondEvaluation());
			existingUserEvaluation.setThirdEvaluation(updatedUserEvaluation.isThirdEvaluation());
			existingUserEvaluation.setFourthEvaluation(updatedUserEvaluation.isFourthEvaluation());
			// evaluation criteria
			existingUserEvaluation.setFirstCriteria(updatedUserEvaluation.isFirstCriteria());
			existingUserEvaluation.setSecondCriteria(updatedUserEvaluation.isSecondCriteria());
			existingUserEvaluation.setThirdCriteria(updatedUserEvaluation.isThirdCriteria());
			existingUserEvaluation.setFourthCriteria(updatedUserEvaluation.isFourthCriteria());
			existingUserEvaluation.setFifthCriteria(updatedUserEvaluation.isFifthCriteria());
			existingUserEvaluation.setSixthCriteria(updatedUserEvaluation.isSixthCriteria());
			existingUserEvaluation.setSeventhCriteria(updatedUserEvaluation.isSeventhCriteria());


			// Calculate the percentage based on  evaluation criteria
			double percentage = calculatePercentage(existingUserEvaluation);
			existingUserEvaluation.setPercentage(percentage);

			return coderEvaluationRepository.save(existingUserEvaluation);
		}

		return null;
	}
	private double calculatePercentage(CoderEvaluation coderEvaluation) {

		int totalCriteria = 7; // Total number of criteria
		int totalPoints = 0;

		if (coderEvaluation.isFirstCriteria()) {
			totalPoints += 1;
		}

		// Calculate the percentage

		return (totalPoints * 100.0) / totalCriteria;
	}
// delete method
	public void deleteCoderEvaluation(Long id) {
		coderEvaluationRepository.deleteById(id);
	}
}
