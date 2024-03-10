package com.spring.office.payroll.dto;

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
    private Double taxInformation;
    private Double travelAllowance;
    private Double travelInformation;
    private Double medicalAllowance;
    private Double medicalInformation;
    private Double providentFund;
    private Double providentInformation;
    private Double bonusAmount;
    private Double reimbursement;
    private Double otherDeduction;
    private Integer workingDays;
    private Integer unpaidLeaveDays;
    private Integer totalLeaveDays;
    private Long employeeId;
    private String employeeName;
    private String departmentName;
    private String jobTitle;
    private String status;

}
