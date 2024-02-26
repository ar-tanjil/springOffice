package com.spring.office.controller;

import com.spring.office.dto.ApplicantTableDto;
import com.spring.office.dto.ApplicationDto;
import com.spring.office.dto.EmployeeDto;
import com.spring.office.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/applications")
@CrossOrigin(origins = "http://localhost:4200")
public class ApplicationController {

    private final ApplicationService service;


    @PostMapping
    public ApplicationDto saveApplication(
            @RequestBody ApplicationDto dto
    ){
        return service.save(dto);
    }

    @GetMapping
    public Iterable<ApplicantTableDto> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ApplicationDto getById(
            @PathVariable("id") Long id
    ){
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id){
        service.delete(id);
    }

    @PutMapping("/{id}")
    public ApplicationDto update(
            @PathVariable("id") Long id,
            @RequestBody ApplicationDto dto
    ) {
        return service.update(id, dto);
    }

    @GetMapping("/recruit/{id}")
    public EmployeeDto recruitApplicant(
            @PathVariable("id") Long id
    ){
        return service.recruitApplicant(id);
  }
}
