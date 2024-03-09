package com.spring.office.payroll.service;

import com.spring.office.employee.Employee;
import com.spring.office.employee.EmployeeMapper;
import com.spring.office.payroll.domain.LeavePolicy;
import com.spring.office.payroll.dto.LeavePolicyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LeavePolicyMapper {

    private final EmployeeMapper employeeMapper;

    public LeavePolicyDto leavePolicyToDto(LeavePolicy policy){
        return LeavePolicyDto.builder()
                .id(policy.getId())
                .medical(policy.getMedical())
                .casual(policy.getCasual())
                .medicalSpent(policy.getMedicalSpent())
                .casualSpent(policy.getCasualSpent())
                .unpaidSpent(policy.getUnpaidSpent())
                .employee(employeeMapper.employeeToTable(policy.getEmployee()))
                .build();
    }

    public LeavePolicy dtoToLeavePolicy(LeavePolicyDto dto){
        LeavePolicy policy = new LeavePolicy();

        if (dto.getEmployeeId() == null){
            return null;
        }

        Employee employee = new Employee();
        employee.setId(dto.getEmployeeId());

        if (dto.getId() != null){
            policy.setId(dto.getId());
        }

        policy.setEmployee(employee);
        policy.setCasual(dto.getCasual());
        policy.setMedical(dto.getMedical());

        return policy;
    }

}
