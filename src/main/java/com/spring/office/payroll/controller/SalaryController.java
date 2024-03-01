package com.spring.office.payroll.controller;

import com.spring.office.payroll.dto.SalaryDto;
import com.spring.office.payroll.dto.SalaryTable;
import com.spring.office.payroll.service.SalaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.invoke.SerializedLambda;

@RestController
@RequestMapping("/salaries")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class SalaryController {

    private final SalaryService salaryService;

    @PostMapping("/{emp_id}")
    public SalaryDto addSalary(
            @PathVariable("emp_id") Long empId,
            @RequestBody SalaryDto dto
    ) {
        dto.setEmployeeId(empId);
        return salaryService.addSalary(dto);
    }

    @PatchMapping("/{emp_id}")
    public SalaryDto updateSalary(
            @PathVariable("emp_id") Long empId,
            @RequestBody SalaryDto dto
    ){
        dto.setEmployeeId(empId);
        return salaryService.updateSalary(dto);
    }


    @GetMapping("/{emp_id}")
    public SalaryDto getSalaryByEmpId(
            @PathVariable("emp_id") Long empId
    ){
        return salaryService.getSalaryByEmployee(empId);
    }

    @GetMapping
    public Iterable<SalaryTable> getAllSalary(){
        return salaryService.getAllSalary();
    }

}
