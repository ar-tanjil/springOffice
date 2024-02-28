package com.spring.office.payroll.domain;


import com.spring.office.domain.BaseModel;
import com.spring.office.employee.Employee;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Additions extends BaseModel {

    private Double travelAllowance;
    private Double bonus;
    private YearMonth period;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

}
