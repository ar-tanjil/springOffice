package com.spring.office.payroll.dto;

import lombok.*;

import java.time.Month;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeductionDto {

    private Long id;
    private Double unpaidLeave;
    private Double loanPayment;
    private Double tax;
    private Integer year;
    private Month month;
    private Long employeeId;
}
