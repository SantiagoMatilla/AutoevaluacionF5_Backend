package com.F5aes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "bootcamp-Stack")
public class StackModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

	@ManyToOne(targetEntity = BootcampModel.class, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "bootcamp_id")
	private BootcampModel bootcampModels;

	@OneToMany(mappedBy = "stackModel")
	private Set<SkillModel> skillModels;
}
