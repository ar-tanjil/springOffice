package com.spring.office.payroll.domain;

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
    private double netSalary;
    private double grossSalary;
    private double basicSalary;
    private double unpaidLeave;
    private double tax;
    private double loanPayment;
    private double bonusAmount;
    private double providentFund;
    private double medicalAllowance;
    private double travelAllowance;
    private double other;


    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

}
