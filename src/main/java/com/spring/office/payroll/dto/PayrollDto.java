package com.spring.office.payroll.dto;

import com.spring.office.employee.Employee;
import lombok.*;

import java.time.YearMonth;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PayrollDto {

    private Long id;
    private YearMonth period;
    private Double unpaidLeave;
    private Double loanPayment;
    private Double tax;
    private Double travelAllowance;
    private Double bonus;
    private Double basic;
    private Double medicalAllowance;
    private Double providentFund;
    private Double netPay;
    private Long employeeId;


}
