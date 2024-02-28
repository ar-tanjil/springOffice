package com.spring.office.payroll.dto;

import com.spring.office.employee.Employee;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.time.Year;
import java.time.YearMonth;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AdditionsDto {

    private Long id;
    private Float travelAllowance;
    private Float bonus;
    private Integer year;
    private Month month;
    private Long employeeId;

}
