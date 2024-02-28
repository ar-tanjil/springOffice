package com.spring.office.payroll.controller;

import com.spring.office.payroll.dto.TaxDto;
import com.spring.office.payroll.service.TaxService;
import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tax")
@RequiredArgsConstructor
public class TaxController {

    private final TaxService taxService;

    @PostMapping
    public TaxDto save(
            @RequestBody TaxDto dto
    ) {
        return taxService.saveTax(dto);
    }

    @GetMapping("/{salary}")
    public Double getTax(
            @PathVariable("salary") Double salary
    ){
        return taxService.taxCalculation(salary);
    }

}
