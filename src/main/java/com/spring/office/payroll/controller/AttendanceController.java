package com.spring.office.payroll.controller;

import com.spring.office.payroll.dto.AttendanceDto;
import com.spring.office.payroll.dto.AttendanceSheet;
import com.spring.office.payroll.dto.TimePeriod;
import com.spring.office.payroll.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@RestController
@RequestMapping("/attendances")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @GetMapping("/{emp_id}")
    public AttendanceDto giveAttendance(
            @PathVariable("emp_id") Long empId
    ) {
        return attendanceService.giveAttendance(empId);
    }

    @PostMapping("/table/{emp_id}")
    public Iterable<AttendanceDto> getById(
            @PathVariable("emp_id") Long empId,
            @RequestBody() TimePeriod period

    ) {
        return attendanceService
                .getEmployeeAttendanceByMonth(empId, period.getStartDate(), period.getEndDate());
    }

    @PostMapping("/table")
    public Iterable<AttendanceDto> getAttendanceTable(
            @RequestBody TimePeriod time
            ){
        return attendanceService.getAttendanceLog(time.getStartDate(), time.getEndDate());
    }

    @GetMapping("/sheet/{start_date}/{end_date}")
    public Iterable<AttendanceSheet> getAttendanceSheet(
            @PathVariable("start_date") LocalDate start,
            @PathVariable("end_date") LocalDate end
    ){


        return attendanceService
                .getAttendanceSheet(start,end);
    }


    @GetMapping("/day/{id}/{date}")
    public AttendanceDto getByDate(
            @PathVariable("id") Long id,
            @PathVariable("date") String date
    ){
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-dd-yyyy");

        var starDate = LocalDate.parse(date);
        return attendanceService.getAttendanceByDay(id, starDate);
    }

    @GetMapping("/count/present")
    public Integer todayPresentEmployee(){
        LocalDate localDate = LocalDate.now();
        return attendanceService.todayPresentEmployee(localDate);
    }



    @GetMapping("/give/employee/{id}")
    public void giveAttendanceByEmployee(
            @PathVariable("id") Long id
    ){
        attendanceService.giveAttendanceByEmployee(id);
    }




}
