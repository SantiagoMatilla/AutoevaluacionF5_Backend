package com.F5aes.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "coder_evaluation")
public class CoderEvaluation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "coder_id")
	private UserModel coder;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "content_id")
	private Content content;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "evaluator_id")
	private UserModel evaluator;

	// Evaluation phases
	private boolean firstEvaluation;
	private boolean secondEvaluation;
	private boolean thirdEvaluation;
	private boolean fourthEvaluation;

	// Evaluation Criteria

	private boolean firstCriteria;
	private boolean secondCriteria;
	private boolean thirdCriteria;
	private boolean fourthCriteria;
	private boolean fifthCriteria;
	private boolean sixthCriteria;
	private boolean seventhCriteria;

	// percentage based on evaluation
	private double percentage;

	public  CoderEvaluation(){

	}
	public CoderEvaluation
			(
			Long id, UserModel coder, Content content, UserModel evaluator,
			boolean firstEvaluation, boolean secondEvaluation, boolean thirdEvaluation, boolean fourthEvaluation,
			boolean firstCriteria, boolean secondCriteria, boolean thirdCriteria, boolean fourthCriteria,
			boolean fifthCriteria, boolean sixthCriteria, boolean seventhCriteria, double percentage
			)
	{
		this.id = id;
		this.coder = coder;
		this.content = content;
		this.evaluator = evaluator;

		// phase of evaluation

		this.firstEvaluation = firstEvaluation;
		this.secondEvaluation = secondEvaluation;
		this.thirdEvaluation = thirdEvaluation;
		this.fourthEvaluation = fourthEvaluation;

		// evaluation criteria
		this.firstCriteria = firstCriteria;
		this.secondCriteria = secondCriteria;
		this.thirdCriteria = thirdCriteria;
		this.fourthCriteria = fourthCriteria;
		this.fifthCriteria = fifthCriteria;
		this.sixthCriteria = sixthCriteria;
		this.seventhCriteria = seventhCriteria;

		// percentage based on evaluation
		this.percentage = percentage;
	}

}
