package com.spring.office.payroll.controller;


import com.spring.office.payroll.dto.OfficeRuleDto;
import com.spring.office.payroll.service.OfficeRuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rules")
@CrossOrigin(origins = "http://localhost:4200")
public class OfficeRuleController {


    private final OfficeRuleService ruleService;


    @GetMapping
    public Iterable<OfficeRuleDto> getAllRule(){
        return ruleService.getAllRules();
    }

    @GetMapping("/{id}")
    public OfficeRuleDto getRuleById(
            @PathVariable("id") Long id
    ){
        return ruleService.getRuleById(id);
    }

    @PutMapping("/{id}")
    public OfficeRuleDto updateRule(
            @PathVariable("id") Long id,
            @RequestBody OfficeRuleDto dto
    ){
        return ruleService.update(dto);
    }

}
