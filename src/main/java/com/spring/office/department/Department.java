package com.spring.office.department;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.office.domain.BaseModel;
import com.spring.office.job.Job;
import com.spring.office.employee.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Department extends BaseModel {

    private String departmentName;


    @OneToOne
    @JoinColumn(name = "manager_id")
    private Employee manager;

    private String departmentDesc;

    @JsonIgnore
    @OneToMany(mappedBy = "department")
    private Set<Employee> employees;

    @JsonIgnore
    @OneToMany(mappedBy = "department")
    private Set<Job> job;

}
