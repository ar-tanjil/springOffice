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
    private Double netSalary;
    private Double grossSalary;
    private Double basicSalary;
    private Double unpaidLeave;
    private Double loanPayment;
    private Double tax;
    private Double travelAllowance;
    private Double medicalAllowance;
    private Double providentFund;
    private Double bonusAmount;
    private Double otherEarning;
    private Double otherDeduction;
    private Long employeeId;

}
