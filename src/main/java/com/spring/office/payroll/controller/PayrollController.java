package com.spring.office.payroll.controller;

import com.spring.office.payroll.dto.PayrollDto;
import com.spring.office.payroll.dto.PayrollTable;
import com.spring.office.payroll.service.PayrollService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("payrolls")
@CrossOrigin(origins = "http://localhost:4200")
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

    @GetMapping("/{year}/{month}")
    public Iterable<PayrollTable> getAllByPeriod(
            @PathVariable("year") Integer year,
            @PathVariable("month") Integer month
    ){
        return payrollService.getAllByPeriod(year, month);
    }

    @GetMapping("/refresh")
    public void refresh(){
        payrollService.deleteAllPayroll();
    }

}
