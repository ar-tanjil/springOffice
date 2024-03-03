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

    @GetMapping("/{id}")
    public TaxDto getTaxById(
            @PathVariable("id") Long id
    ){
        return taxService.getById(id);
    }

    @GetMapping
    public Iterable<TaxDto> getAll(){
        return taxService.getAllTax();
    }

}
