package com.spring.office.controller;

import com.spring.office.dto.DepartmentDto;
import com.spring.office.dto.JobDto;
import com.spring.office.dto.Message;
import com.spring.office.service.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobService service;

    public JobController(
            JobService service
    ) {
        this.service = service;
    }


    @GetMapping
    public Iterable<JobDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public JobDto getById(@PathVariable("id") Long id) {
        return service.getById(id);
    }

    @PostMapping
    public JobDto saveEmployee(@RequestBody JobDto dto) {
        return service.save(dto);
    }

    @PutMapping("/{id}")
    public JobDto updateEmployee(@PathVariable("id") Long id,
                                 @RequestBody JobDto dto) {
        return service.update(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Message> deleteById(@PathVariable("id") Long id) {
        boolean success = service.delete(id);
        if (success) {
            Message successMsg = new Message("Success");
            return new ResponseEntity<>(successMsg, HttpStatus.ACCEPTED);
        }
        Message failedMsg = new Message("Not Found");
        return new ResponseEntity<>(failedMsg, HttpStatus.NOT_FOUND);
    }


}
