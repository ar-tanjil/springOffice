package com.spring.office.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class Department extends BaseModel {

    private String departmentName;
    private Long managerId;
    private String departmentDesc;

    @OneToMany(mappedBy = "department",
            cascade = CascadeType.ALL)
    private Set<Employee> employees;
}
