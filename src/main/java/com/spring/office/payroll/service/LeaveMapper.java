package com.spring.office.payroll.service;

import com.spring.office.employee.Employee;
import com.spring.office.payroll.domain.Leave;
import com.spring.office.payroll.dto.LeaveDto;
import org.springframework.stereotype.Service;

@Service
public class LeaveMapper {


    public LeaveDto leaveToDto(Leave leave){
        return LeaveDto.builder()
                .id(leave.getId())
                .employeeId(leave.getEmployee().getId())
                .day(leave.getDay())
                .status(leave.isStatus())
                .reason(leave.getReason())
                .type(leave.getType())
                .build();
    }


    public Leave dtoToLeave(LeaveDto dto){
        if (dto.getEmployeeId() == null){
            return null;
        }

        Employee emp = new Employee();
        emp.setId(dto.getId());

        return Leave.builder()
                .id(dto.getId())
                .employee(emp)
                .day(dto.getDay())
                .reason(dto.getReason())
                .type(dto.getType())
                .status(dto.isStatus())
                .build();
    }

}
