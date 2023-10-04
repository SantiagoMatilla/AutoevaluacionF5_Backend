package com.F5aes.model;

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
@Table(name = "skills")

public class SkillModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String Name;

    @ManyToOne(targetEntity = StackModel.class, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "stack_id")
    private StackModel stackModel;

    @OneToMany(mappedBy = "skillModel")
    private Set<ContentModel> contents;

}
