package com.spring.office.payroll.controller;

import com.spring.office.payroll.dto.PayrollDto;
import com.spring.office.payroll.service.PayrollService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("payroll")
public class PayrollController {

    private final PayrollService payrollService;

    @GetMapping("/{emp_id}/{year}/{month}")
    public PayrollDto getPayroll(
            @PathVariable("emp_id") Long empId,
            @PathVariable("year") Integer year,
            @PathVariable("month") Integer month
    ) {
        return payrollService.getPayrollByEmpAndPeriod(empId, year, month);
    }
}
