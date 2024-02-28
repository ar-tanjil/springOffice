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
    private Double basic;
    private Double medicalAllowance;
    private Double providentFund;
    private Long employeeId;

}
