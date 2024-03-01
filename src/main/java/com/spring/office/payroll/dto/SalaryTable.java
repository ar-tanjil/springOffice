package com.spring.office.payroll.dto;

public record SalaryTable (
        Long id,
        Double basic,
        Double medicalAllowance,
        Double providentFund,
        Long employeeId,
        String firstName,
        String jobTitle,
        String departmentName
) {

}
