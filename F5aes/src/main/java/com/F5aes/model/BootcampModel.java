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
@Table(name = "bootcamp")
public class BootcampModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String duration;

    private Date startDate;
    private Date endDate;

    @OneToMany(mappedBy = "bootcampModels")
    private Set<UserModel> userModels;

    @OneToMany(mappedBy = "bootcampModels")
    private Set<StackModel> stacksModels;
}
