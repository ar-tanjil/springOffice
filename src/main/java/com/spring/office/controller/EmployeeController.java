package com.spring.office.controller;

import com.spring.office.dto.EmpResDto;
import com.spring.office.dto.EmployeeDto;
import com.spring.office.dto.Message;
import com.spring.office.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
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
    public List<EmpResDto> getAll() {
        return empService.getAll();
    }

    @GetMapping("/{id}")
    public EmployeeDto getById(@PathVariable("id") Long id){
        return empService.getById(id);
    }

    @PostMapping
    public EmployeeDto saveEmployee(@RequestBody EmployeeDto dto){
        return empService.save(dto);
    }

    @PutMapping("/{id}")
    public EmployeeDto updateEmployee(@PathVariable("id") Long id,
                                      @RequestBody EmployeeDto dto){
        return empService.update(dto);
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
