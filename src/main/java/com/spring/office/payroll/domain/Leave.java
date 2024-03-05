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

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "_leave")
public class Leave extends BaseModel {

    private LocalDate day;
    private String type;
    private String reason;
    private boolean status;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
