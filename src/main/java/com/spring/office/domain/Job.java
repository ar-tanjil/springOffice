package com.spring.office.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class Job extends BaseModel{

    private String jobTitle;
    private Integer minSalary;
    private Integer maxSalary;

    @OneToMany(mappedBy = "job",
            cascade = CascadeType.ALL)
    private Set<Employee> employees;
}
