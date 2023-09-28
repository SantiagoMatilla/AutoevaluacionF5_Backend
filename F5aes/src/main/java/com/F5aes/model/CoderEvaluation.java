package com.F5aes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CoderEvaluation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "coder_id")
	private UserModel coder;
	@ManyToOne
	@JoinColumn(name = "content_id")
	private ContentModel content;
	//Evaluations
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
}
