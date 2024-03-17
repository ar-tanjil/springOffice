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

    @GetMapping("employee/{emp_id}/{year}/{month}")
    public PayrollDto getPayroll(
            @PathVariable("emp_id") Long empId,
            @PathVariable("year") Integer year,
            @PathVariable("month") Integer month
    ) {
        return payrollService.getPayrollByEmpAndPeriod(empId, year, month);
    }

    @GetMapping("/process/{year}/{month}")
    public Iterable<PayrollTable> processAllByPeriod(
            @PathVariable("year") Integer year,
            @PathVariable("month") Integer month
    ){
        return payrollService.processAllPayroll(year, month);
    }

    @GetMapping("/process/employee/{id}/{year}/{month}")
    public void processByEmpPeriod(
            @PathVariable("id") Long id,
            @PathVariable("year") Integer year,
            @PathVariable("month") Integer month
    ){
         payrollService.processPayrollByEmployee(id, year, month);
    }

    @GetMapping("/pending")
    public Iterable<PayrollTable> getAllPendingPayroll(){
        return payrollService.getPendingPayroll();
    }

    @GetMapping("/payment")
    public Iterable<PayrollTable> getAllPaymentPayroll(){
        return payrollService.getPaymentPayroll();
    }

    @GetMapping("/refresh")
    public void refresh(){
        payrollService.deleteAllPayroll();
    }


    @DeleteMapping("/{id}")
    public void deletePayroll(
            @PathVariable("id") Long id
    ){
        payrollService.deletePayroll(id);
    }

    @GetMapping("/{id}")
    public PayrollDto getPayrollById(
            @PathVariable("id") Long id
    ){
        return payrollService.getPayrollById(id);
    }

    @GetMapping("/payment/{id}")
    public void makePayment(
            @PathVariable("id") Long id
    ){
        payrollService.completePayment(id);
    }


    @GetMapping("/employee/payment/{id}/{year}/{month}")
    public Iterable<PayrollTable> getPaymentByPeriod(
            @PathVariable("id") Long empId,
            @PathVariable("year") Integer year,
            @PathVariable("month") Integer month
    ){
        return payrollService.getPaymentPayrollByEmp(empId, year, month);
    }

    @GetMapping("/employee/payment/all/{year}/{month}")
    public Iterable<PayrollTable> getAllPaymentByPeriod(
            @PathVariable("year") Integer year,
            @PathVariable("month") Integer month
    ){
        return payrollService.getAllPaymentPayroll(year, month);
    }

}
