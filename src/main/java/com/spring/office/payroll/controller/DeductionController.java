package com.spring.office.payroll.controller;

import com.spring.office.payroll.dto.DeductionAddDto;
import com.spring.office.payroll.dto.DeductionDto;
import com.spring.office.payroll.service.DeductionsService;
import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deductions")
@RequiredArgsConstructor
public class DeductionController {

    private final DeductionsService deductionsService;

    @PostMapping("/{emp_id}/{year}/{month}")
    public DeductionDto addDeductionsByPeriod(
            @PathVariable("emp_id") Long empId,
            @PathVariable("year") Integer year,
            @PathVariable("month") Integer month,
            @RequestBody DeductionAddDto dto
    ){
        return deductionsService.saveDeductions(empId,year,month,dto);
    }

    @GetMapping("/{emp_id}/{year}/{month}")
    public DeductionDto getDeductionsByPeriod(
            @PathVariable("emp_id") Long empId,
            @PathVariable("year") Integer year,
            @PathVariable("month") Integer month
    ){
        return deductionsService.getByEmpIdAndPeriod(empId,year,month);
    }

}
