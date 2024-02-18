package com.spring.office.controller;

import com.spring.office.dto.EmployeeDto;
import com.spring.office.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeService empService;

    private EmployeeController(EmployeeService employeeService) {
        this.empService = employeeService;
    }

    @GetMapping
    public List<EmployeeDto> getAll() {
        return empService.getAll();
    }
}
