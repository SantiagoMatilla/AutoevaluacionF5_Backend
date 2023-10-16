package com.F5aes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "users")
public class UserModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	private String phone;
	private String email;
	private String password;
	private String repeatPassword;

	@ManyToMany(fetch = FetchType.EAGER, targetEntity = Role.class, cascade = CascadeType.PERSIST)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	public void addRole(Role role) {
		roles.add(role);
	}

	@ManyToOne(targetEntity = Bootcamp.class, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "bootcamp_id" , referencedColumnName = "id")
	private Bootcamp bootcamp;

	public void setBootcampName(String name) {
	}
	@OneToMany(mappedBy = "evaluator")
	@JsonIgnore
	private Set<CoderEvaluation> evaluationPerformed;


}
