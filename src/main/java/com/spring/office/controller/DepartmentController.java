package com.spring.office.controller;

import com.spring.office.dto.DepartReceiveDto;
import com.spring.office.dto.Message;
import com.spring.office.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departments")
@CrossOrigin(origins = "http://localhost:4200")
public class DepartmentController {

    private final DepartmentService service;

    public DepartmentController(DepartmentService service){
        this.service = service;
    }

    @GetMapping
    public Iterable<DepartReceiveDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public DepartReceiveDto getById(@PathVariable("id") Long id){
        return service.getById(id);
    }

    @PostMapping
    public DepartReceiveDto saveEmployee(@RequestBody DepartReceiveDto dto){
        return service.save(dto);
    }

    @PutMapping("/{id}")
    public DepartReceiveDto updateEmployee(@PathVariable("id") Long id,
                                           @RequestBody DepartReceiveDto dto){
        return service.update(id,dto);
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
