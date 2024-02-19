package com.spring.office.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Set<Employee> employees;
}
