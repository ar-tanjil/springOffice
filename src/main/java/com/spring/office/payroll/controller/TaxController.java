package com.spring.office.payroll.controller;

import com.spring.office.payroll.dto.TaxDto;
import com.spring.office.payroll.service.TaxService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/taxes")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
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
    ) {
        return taxService.taxCalculation(salary);
    }

}
