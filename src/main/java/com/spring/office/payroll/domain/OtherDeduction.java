package com.spring.office.payroll.domain;


import com.spring.office.domain.BaseModel;
import com.spring.office.employee.Employee;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.time.YearMonth;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OtherDeduction extends BaseModel {
    private YearMonth period;
    private double amount;
    private boolean status;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
