package com.spring.office.payroll.dto;

import lombok.*;

import java.time.YearMonth;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PayrollTable {
    private Long employeeId;
    private String firstName;
    private Double netPay;
    private YearMonth period;
}
