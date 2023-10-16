package com.F5aes.model;

import java.sql.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "bootcamp")
public class Bootcamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String duration;

    private Date startDate;
    private Date endDate;

    @OneToMany(mappedBy = "bootcamp")
    @JsonIgnore
    private Set<UserModel> userModels;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Stack.class, cascade = CascadeType.PERSIST)
    @JoinTable(name = "bootcamp_stacks", joinColumns = @JoinColumn(name = "bootcamp_id"), inverseJoinColumns = @JoinColumn(name = "stack_id"))
    @JsonIgnore
    private Set<Stack> stacksModels;


}
