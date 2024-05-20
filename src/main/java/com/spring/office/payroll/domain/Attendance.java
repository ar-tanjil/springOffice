package com.spring.office.payroll.domain;


import com.spring.office.domain.BaseModel;
import com.spring.office.employee.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Attendance extends BaseModel {

    private LocalDate day;
    private LocalTime checkIn;
    private LocalTime checkOut;
    @Enumerated(EnumType.STRING)
    private AttendanceStatus checkInStatus;
    @Enumerated(EnumType.STRING)
    private AttendanceStatus checkOutStatus;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

}
