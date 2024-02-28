package com.spring.office.payroll.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class SalaryDto {

    private Long id;
    private float basic;
    private float medicalAllowance;
    private float providentFund;
    private Long employeeId;

}
