package com.spring.office.controller;

import com.spring.office.dto.ApplicationDto;
import com.spring.office.service.ApplicationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/applications")
@CrossOrigin(origins = "http://localhost:4200")
public class ApplicationController {

    private final ApplicationService service;

    public ApplicationController(ApplicationService service){
        this.service = service;
    }

    @PostMapping
    public ApplicationDto saveApplication(
            @RequestBody ApplicationDto dto
    ){
        return service.save(dto);
    }

    @GetMapping
    public Iterable<ApplicationDto> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ApplicationDto getById(
            @PathVariable("id") Long id
    ){
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public ApplicationDto update(
            @PathVariable("id") Long id,
            @RequestBody ApplicationDto dto
    ) {
        return service.update(id, dto);
    }
}
