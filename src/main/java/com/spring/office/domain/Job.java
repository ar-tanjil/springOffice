package com.spring.office.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Job extends BaseModel{

    private String jobTitle;
    private Integer minSalary;
    private Integer maxSalary;
    private Integer totalPost;

    @OneToMany(mappedBy = "job")
    @JsonIgnore
    private Set<Employee> employees;

    @OneToMany(mappedBy = "job")
    @JsonIgnore
    private Set<Application> applications;

    @ManyToMany(mappedBy = "job")
    @JsonIgnore
    private Set<Department> departments;

}
