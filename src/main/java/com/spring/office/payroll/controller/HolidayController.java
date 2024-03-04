package com.spring.office.payroll.controller;

import com.spring.office.payroll.domain.Holiday;
import com.spring.office.payroll.dto.HolidayDto;
import com.spring.office.payroll.service.HolidayService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RequiredArgsConstructor
@RestController
@RequestMapping("/holidays")
@CrossOrigin(origins = "http://localhost:4200")
public class HolidayController {

    private final HolidayService holidayService;

    @GetMapping
    public Iterable<HolidayDto> getAllHoliday() {
        return this.holidayService.getAllHoliday();
    }

    @GetMapping("/{id}")
    public HolidayDto getHolidayById(
            @PathVariable("id") Long id
    ) {
        return this.holidayService.getHolidayById(id);
    }

    @PostMapping
    public HolidayDto saveHoliday(
            @RequestBody HolidayDto holidayDto
    ) {
        return holidayService.addHoliday(holidayDto);
    }

    @PutMapping
    public HolidayDto updateHoliday(
            @RequestBody HolidayDto dto
    ) {
        return holidayService.updateHoliday(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteHoliday(
            @PathVariable("id") Long id
    ) {
        holidayService.deleteHoliday(id);
    }


    @GetMapping("/check/{day}")
    public boolean checkHoliday(
            @PathVariable LocalDate day
    ) {
        return this.holidayService.checkHoliday(day);
    }

}
