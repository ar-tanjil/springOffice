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
public class Job extends BaseModel{

    private String jobTitle;
    private Integer minSalary;
    private Integer maxSalary;

    @OneToMany(mappedBy = "job",
            cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Employee> employees;
}
