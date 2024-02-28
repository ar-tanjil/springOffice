package com.spring.office.payroll.controller;

import com.spring.office.payroll.dto.AttendanceDto;
import com.spring.office.payroll.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@RestController
@RequestMapping("/attendances")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping("/{emp_id}")
    public AttendanceDto giveAttendance(
            @PathVariable("emp_id") Long empId
    ) {
        return attendanceService.giveAttendance(empId);
    }

    @GetMapping("/{emp_id}")
    public Iterable<AttendanceDto> getById(
            @PathVariable("emp_id") Long empId,
            @RequestParam("star_date") LocalDate start,
            @RequestParam("end_date") LocalDate end
    ) {
        return attendanceService.getEmployeeAttendanceBetweenTime(empId, start, end);
    }
}
