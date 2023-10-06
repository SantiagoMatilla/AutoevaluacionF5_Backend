package com.F5aes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "bootcamp-skills")

public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String name;

    @ManyToOne(targetEntity = Stack.class, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "stack_id" ,referencedColumnName = "id")
    private Stack stack;

    @OneToMany(mappedBy = "skill")
    @JsonIgnore
    private Set<Content> contents;

}
