package com.spring.office.controller;

import com.spring.office.dto.DepartmentDto;
import com.spring.office.dto.EmployeeDto;
import com.spring.office.dto.Message;
import com.spring.office.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService service;

    public DepartmentController(DepartmentService service){
        this.service = service;
    }

    @GetMapping
    public Iterable<DepartmentDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public DepartmentDto getById(@PathVariable("id") Long id){
        return service.getById(id);
    }

    @PostMapping
    public DepartmentDto saveEmployee(@RequestBody DepartmentDto dto){
        return service.save(dto);
    }

    @PutMapping("/{id}")
    public DepartmentDto updateEmployee(@PathVariable("id") Long id,
                                      @RequestBody DepartmentDto dto){
        return service.update(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Message> deleteById(@PathVariable("id") Long id){
        boolean success = service.delete(id);
        if (success){
            Message successMsg = new Message("Success");
            return new ResponseEntity<>(successMsg, HttpStatus.ACCEPTED);
        }
        Message failedMsg = new Message("Not Found");
        return new ResponseEntity<>(failedMsg, HttpStatus.NOT_FOUND);
    }



}
