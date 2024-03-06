package com.spring.office.payroll.service;

import com.spring.office.payroll.domain.Attendance;
import com.spring.office.payroll.dto.AttendanceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

        return AttendanceDto.builder()
                .id(att.getId())
                .day(att.getDay())
                .entryTime(att.getCheckIn())
                .leaveTime(att.getCheckOut())
                .present(att.isPresent())
                .employeeId(att.getEmployee().getId())
                .build();
    }

}
