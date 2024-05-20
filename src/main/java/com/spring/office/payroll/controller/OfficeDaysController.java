package com.spring.office.payroll.controller;

import com.spring.office.payroll.dto.OfficeDaysDto;
import com.spring.office.payroll.service.OfficeDaysService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;

@RestController
@RequestMapping("/days")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class OfficeDaysController {

    private final OfficeDaysService officeDaysService;

    @GetMapping
    public Iterable<OfficeDaysDto> getAllDays(){
        return officeDaysService.getAllDays();
    }


    @PutMapping("/{id}")
    public OfficeDaysDto updateDays(
            @RequestBody OfficeDaysDto dto
    ){
        return officeDaysService.updateDays(dto);
    }

    @GetMapping("/{id}")
    public OfficeDaysDto getById(
            @PathVariable("id") Long id
    ){
        return officeDaysService.getDaysById(id);

    }


}
