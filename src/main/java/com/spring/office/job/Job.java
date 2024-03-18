package com.spring.office.job;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.office.department.Department;
import com.spring.office.application.Application;
import com.spring.office.domain.BaseModel;
import com.spring.office.employee.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Job extends BaseModel {

    private String jobTitle;
    private int minSalary;
    private int maxSalary;
    private int totalPost;
    private int vacancy;

    @ElementCollection
    private List<String> requirements;

    @OneToMany(mappedBy = "job")
    @JsonIgnore
    private Set<Employee> employees;

    @OneToMany(mappedBy = "job")
    @JsonIgnore
    private Set<Application> applications;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    @JsonIgnore
    private Department department;

}
