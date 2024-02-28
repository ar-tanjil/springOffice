package com.spring.office.payroll.domain;


import com.spring.office.domain.BaseModel;
import com.spring.office.employee.Employee;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.YearMonth;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Additions extends BaseModel {

    private Float travelAllowance;
    private Float bonus;
    private YearMonth month;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

}
