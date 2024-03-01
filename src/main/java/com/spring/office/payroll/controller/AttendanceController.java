package com.spring.office.payroll.controller;

import com.spring.office.payroll.dto.AttendanceDto;
import com.spring.office.payroll.dto.AttendanceTable;
import com.spring.office.payroll.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@RestController
@RequestMapping("/attendances")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping("/{emp_id}")
    public AttendanceDto giveAttendance(
            @PathVariable("emp_id") Long empId
    ) {
        return attendanceService.giveAttendance(empId);
    }

    @GetMapping("/{emp_id}/{start_date}/{end_date}")
    public Iterable<AttendanceDto> getById(
            @PathVariable("emp_id") Long empId,
            @PathVariable("star_date") LocalDate start,
            @PathVariable("end_date") LocalDate end
    ) {
        return attendanceService
                .getEmployeeAttendanceBetweenTime(empId, start, end);
    }

    @GetMapping("/{start_date}/{end_date}")
    public Iterable<AttendanceTable> getById(
            @PathVariable("start_date") String start,
            @PathVariable("end_date") String end
    ){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-dd-yyyy");

        var starDate = LocalDate.parse(start);
        var endDate = LocalDate.parse(end);
        return attendanceService
                .getAttendanceSheet(starDate,endDate);
    }


}
