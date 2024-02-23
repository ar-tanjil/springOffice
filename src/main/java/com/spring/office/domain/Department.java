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
public class Department extends BaseModel {

    private String departmentName;
    private Long managerId;
    private String departmentDesc;

    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private Set<Employee> employees;

    @JsonIgnore
    @OneToMany(mappedBy = "department")
    private Set<Job> job;

}
