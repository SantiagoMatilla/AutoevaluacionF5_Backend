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
@Table(name = "categories")

public class CategoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Name;
    private String Competency;

    @OneToMany(targetEntity = StacksModel.class, cascade = CascadeType.PERSIST)
    @JoinTable(name = "category_stacks", joinColumns = @JoinColumn(name = "category_id"), inverseJoinColumns =
    @JoinColumn(name =	"stack_id"))
    private Set<StacksModel> stacksModels;
}
