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
@Table(name = "users")
public class UserModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	private Long phone;
	private String email;
	private String password;
	private String repeatPassword;

	@ManyToMany(fetch = FetchType.EAGER, targetEntity = RoleModel.class, cascade = CascadeType.PERSIST)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<RoleModel> roleModels;

	public void addRole(RoleModel role) {
		roleModels.add(role);
	}

	@ManyToOne(targetEntity = BootcampModel.class, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "bootcamp_id")
	private BootcampModel bootcampModels;

}
