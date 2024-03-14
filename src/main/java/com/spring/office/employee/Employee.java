package com.spring.office.employee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.office.application.Application;
import com.spring.office.department.Department;
import com.spring.office.domain.*;
import com.spring.office.domain.embaded.Address;
import com.spring.office.domain.embaded.Qualification;
import com.spring.office.job.Job;
import com.spring.office.job.JobHistory;
import com.spring.office.payroll.domain.Leave;
import com.spring.office.payroll.domain.LeavePolicy;
import com.spring.office.payroll.domain.Salary;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    @JsonIgnore
    private Job job;

    @ManyToOne(fetch = FetchType.LAZY)
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
    private Address address;

    @Embedded
    private Qualification qualification;

    @OneToOne()
    @JsonIgnore
    @JoinColumn(name = "application_id")
    private Application application;

    @OneToOne(mappedBy = "employee")
    @JsonIgnore
    private Salary salary;

    @OneToOne(mappedBy = "employee")
    @JsonIgnore
    private LeavePolicy leavePolicy;

    @JsonIgnore
    @OneToMany(mappedBy = "employee")
    private Set<Leave> leaves;

}
