package com.spring.office.payroll.domain;


import com.spring.office.domain.BaseModel;
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
public class Deductions extends BaseModel {

    private float unpaidLeave;
    private float loanPayment;
    private float tax;
    private float insurance;

    @OneToOne
    @JoinColumn(name = "salary_id")
    private Salary salary;

}
