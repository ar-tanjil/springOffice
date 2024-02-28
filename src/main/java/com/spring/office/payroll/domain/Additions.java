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

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Additions extends BaseModel {

    private float travelAllowance;
    private float medicalAllowance;
    private float bonus;
    private LocalDate date;

    @OneToOne
    @JoinColumn(name = "salary_id")
    private Salary salary;

}
