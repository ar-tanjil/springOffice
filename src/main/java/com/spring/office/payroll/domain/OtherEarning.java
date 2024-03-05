package com.spring.office.payroll.domain;

import com.spring.office.domain.BaseModel;
import com.spring.office.employee.Employee;
import jakarta.persistence.*;
import lombok.*;

import java.time.YearMonth;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OtherEarning extends BaseModel {

    private YearMonth period;
    private double amount;
    private boolean status;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;


}
