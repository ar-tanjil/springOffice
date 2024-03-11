package com.spring.office.payroll.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.spring.office.domain.BaseModel;
import com.spring.office.employee.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Claim extends BaseModel {

    private String title;

    @Enumerated(EnumType.STRING)
    private ClaimStatus claimStatus;
    private double amount;
    private LocalDate date;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category")
    @JsonManagedReference
    private ClaimCategory claimCategory;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonIgnore
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "payroll_id")
    @JsonIgnore
    private Payroll payroll;
}
