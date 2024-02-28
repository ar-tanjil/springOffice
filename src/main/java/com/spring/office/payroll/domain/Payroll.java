package com.spring.office.payroll.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.office.domain.BaseModel;
import com.spring.office.employee.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.YearMonth;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Payroll extends BaseModel {

    private YearMonth period;
    private double netPay;

    @OneToOne
    @JoinColumn(name = "deduction_id")
    private Deductions deductions;

    @OneToOne
    @JoinColumn(name = "addition_id")
    private Additions additions;

    @ManyToOne
    @JoinColumn(name = "salary_id")
    private Salary salary;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;



}
