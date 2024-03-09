package com.spring.office.payroll.domain;

import com.spring.office.domain.BaseModel;
import com.spring.office.employee.Employee;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class LeavePolicy extends BaseModel {

    private int medical;
    private int casual;
    private int medicalSpent;
    private int casualSpent;
    private int unpaidSpent;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

}
