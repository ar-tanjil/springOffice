package com.spring.office.payroll.dto;

import lombok.*;

import java.time.YearMonth;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PayrollTable implements Comparable<PayrollTable> {
    private Long id;
    private Long employeeId;
    private String jobTitle;
    private String departmentName;
    private String firstName;
    private Double grossSalary;
    private Double netSalary;
    private YearMonth period;

    @Override
    public int compareTo(PayrollTable o) {
        return this.getGrossSalary().compareTo(o.grossSalary);
    }
}
