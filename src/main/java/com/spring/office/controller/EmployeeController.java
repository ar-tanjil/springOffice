package com.spring.office.controller;

import com.spring.office.dto.EmpTableDto;
import com.spring.office.dto.EmpDetailsDto;
import com.spring.office.dto.Message;
import com.spring.office.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

    private final EmployeeService empService;

    private EmployeeController(EmployeeService employeeService) {
        this.empService = employeeService;
    }

    @GetMapping
    public List<EmpTableDto> getAll() {
        return empService.getAll();
    }

    @GetMapping("/{id}")
    public EmpDetailsDto getById(@PathVariable("id") Long id){
        return empService.getById(id);
    }

    @PostMapping
    public EmpDetailsDto saveEmployee(
           @Valid @RequestBody EmpDetailsDto dto){
        return empService.save(dto);
    }

    @PutMapping("/{id}")
    public EmpDetailsDto updateEmployee(@PathVariable("id") Long id,
                                        @RequestBody EmpDetailsDto dto){
        return empService.update(id,dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Message> deleteById(@PathVariable("id") Long id){
        boolean success = empService.delete(id);
        if (success){
            Message successMsg = new Message("Success");
            return new ResponseEntity<>(successMsg,HttpStatus.ACCEPTED);
        }
        Message failedMsg = new Message("Not Found");
        return new ResponseEntity<>(failedMsg, HttpStatus.NOT_FOUND);
    }
}
