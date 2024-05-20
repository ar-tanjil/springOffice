package com.spring.office.payroll.service;

import com.spring.office.payroll.domain.Attendance;
import com.spring.office.payroll.dto.AttendanceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendanceMapper {

    public List<AttendanceDto> attendanceToDtoList(List<Attendance> att){
       return att.stream().map(this::attendanceToDto)
                .collect(Collectors.toList());
    }




    public AttendanceDto attendanceToDto(Attendance att){

        if (att.getEmployee() == null){
            return null;
        }

        DateTimeFormatter timeFormat = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM);

        String in = "";
        String out = "";

        if (att.getCheckIn() != null){
            in = timeFormat.format(att.getCheckIn());
        }

        if (att.getCheckOut() != null){
            out = timeFormat.format(att.getCheckOut());
        }



        return AttendanceDto.builder()
                .id(att.getId())
                .day(att.getDay())
                .checkIn(in)
                .checkOut(out)
                .checkInStatus(att.getCheckInStatus())
                .checkOutStatus(att.getCheckOutStatus())
                .employeeId(att.getEmployee().getId())
                .employeeName(att.getEmployee().getFirstName())
                .build();
    }

}
