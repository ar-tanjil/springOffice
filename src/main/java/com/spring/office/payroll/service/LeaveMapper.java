package com.spring.office.payroll.service;

import com.spring.office.employee.Employee;
import com.spring.office.payroll.domain.Leave;
import com.spring.office.payroll.domain.LeaveStatus;
import com.spring.office.payroll.domain.LeaveType;
import com.spring.office.payroll.dto.LeaveDto;
import org.springframework.stereotype.Service;

@Service
public class LeaveMapper {


    public LeaveDto leaveToDto(Leave leave){

        String name = "";

        if (leave.getEmployee() != null){
            Employee emp = leave.getEmployee();
            name = emp.getFirstName()
                    + " " + emp.getLastName();
        }


        return LeaveDto.builder()
                .id(leave.getId())
                .employeeId(leave.getEmployee().getId())
                .day(leave.getDay())
                .status(leave.getStatus().name())
                .reason(leave.getReason())
                .type(leave.getType().name())
                .employeeName(name)
                .build();
    }


    public Leave dtoToLeave(LeaveDto dto){
        if (dto.getEmployeeId() == null){
            return null;
        }

        LeaveStatus status = LeaveStatus.PENDING;
        if (dto.getStatus() != null){
            status = LeaveStatus.valueOf(dto.getStatus().toUpperCase());
        }

        Employee emp = new Employee();
        emp.setId(dto.getEmployeeId());

        return Leave.builder()
                .id(dto.getId())
                .employee(emp)
                .day(dto.getDay())
                .reason(dto.getReason())
                .type(LeaveType.valueOf(dto.getType().toUpperCase()))
                .status(status)
                .build();
    }

}
