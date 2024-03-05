package com.spring.office.payroll.dto;

import com.spring.office.employee.Employee;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.time.YearMonth;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OtherEarnDedDto {

    private Long id;
    private Integer year;
    private Integer month;
    private Double amount;
    private Boolean status;
    private Long employeeId;

}
