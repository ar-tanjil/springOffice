package com.spring.office.payroll.controller;

import com.spring.office.payroll.dto.AdditionsAddDto;
import com.spring.office.payroll.dto.AdditionsDto;
import com.spring.office.payroll.service.AdditionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/additions")
@CrossOrigin(origins = "http://localhost:4200")
public class AdditionsController {

    private final AdditionService additionService;

    @PostMapping("/{emp_id}/{year}/{month}")
    public AdditionsDto saveAdditionByPeriod(
            @PathVariable("emp_id") Long empId,
            @PathVariable("year") Integer year,
            @PathVariable("month") Integer month,
            @RequestBody AdditionsAddDto dto
    ){

        return additionService.addAdditionByPeriod(empId,year,month,dto);
    }


    @GetMapping("/{emp_id}/{year}/{month}")
    public AdditionsDto getAdditionByPeriod(
            @PathVariable("emp_id") Long empId,
            @PathVariable("year") Integer year,
            @PathVariable("month") Integer month
    ){

        return additionService.getAdditionsByPeriod(empId,year,month);
    }

}
