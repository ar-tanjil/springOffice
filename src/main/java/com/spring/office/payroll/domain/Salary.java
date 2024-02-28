package com.spring.office.payroll.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.office.domain.BaseModel;
import com.spring.office.employee.Employee;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Salary extends BaseModel {


    private Float basic;
    private Float medicalAllowance;
    private Float providentFund;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

}
