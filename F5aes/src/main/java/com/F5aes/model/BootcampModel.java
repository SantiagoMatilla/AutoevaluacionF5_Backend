package com.F5aes.model;

import java.sql.Date;
import java.util.Set;

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
@Table(name = "bootcamps")
public class BootcampModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String duration;

    private Date startDate;

    private Date endDate;
    @ManyToOne(targetEntity = StacksModel.class, cascade = CascadeType.PERSIST)
    @JoinTable(name = "bootcamp_stacks", joinColumns = @JoinColumn(name = "bootcamp_id"), inverseJoinColumns =
    @JoinColumn(name =	"stack_id"))
    private Set<StacksModel> stacksModels;
}
