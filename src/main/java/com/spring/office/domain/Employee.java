package com.spring.office.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.office.domain.embaded.Address;
import com.spring.office.domain.embaded.Qualification;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Employee extends BaseModel {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate hireDate;
    private LocalDate separationDate;
    private LocalDate dob;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @ManyToOne
    @JoinColumn(name= "department_id")
    @JsonIgnore
    private Department department;


    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name ="emp_job_history",
            joinColumns = {
                    @JoinColumn(name = "employee_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "jobHistory_id")
            }
    )
    private List<JobHistory> jobHistory;

    @Embedded
    public Address address;

    @Embedded
    public Qualification qualification;

    @OneToOne()
    @JoinColumn(name = "application_id")
    public Application application;

}
