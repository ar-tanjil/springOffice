package com.spring.office.payroll.controller;

import com.spring.office.payroll.dto.AdditionsDto;
import com.spring.office.payroll.service.AdditionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/additions")
public class AdditionsController {

    private final AdditionService additionService;

    @PostMapping("/{emp_id}")
    public AdditionsDto saveAddition(
            @PathVariable("emp_id") Long empId,
            @RequestBody AdditionsDto dto
    ){
        dto.setEmployeeId(empId);
        return additionService.addAddition(dto);
    }


}
