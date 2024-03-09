package com.spring.office.payroll.service;

import com.spring.office.employee.Employee;
import com.spring.office.payroll.domain.LeavePolicy;
import com.spring.office.payroll.dto.LeavePolicyDto;
import com.spring.office.payroll.repo.LeavePolicyRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeavePolicyService {

    private final LeavePolicyRepo leavePolicyRepo;
    private final LeavePolicyMapper leavePolicyMapper;
    public LeavePolicyDto saveLeavePolicy(LeavePolicyDto dto){
        var save = leavePolicyRepo.save(leavePolicyMapper.dtoToLeavePolicy(dto));

        return leavePolicyMapper.leavePolicyToDto(save);
    }

    public LeavePolicyDto getLeavePolicyByEmpId(Long id){

        Employee employee = new Employee();
        employee.setId(id);

        return leavePolicyRepo.findByEmployee(employee)
                .map(leavePolicyMapper::leavePolicyToDto)
                .orElse(new LeavePolicyDto());
    }

    public boolean updateMedicalLeave(Long employeeId, int day){
        Employee emp = new Employee();
        emp.setId(employeeId);

        LeavePolicy policy = leavePolicyRepo.findByEmployee(emp).orElse(null);

        if (policy == null){
            return false;
        }


        if (!checkMedicalLeave(policy, day)){
            return false;
        }

        leavePolicyRepo.updateMedicalSpent(emp,day);
        return true;
    }
    public boolean checkMedicalLeave(LeavePolicy policy, int day){
        int remainingLeave = policy.getMedical() - policy.getMedicalSpent();
        if (remainingLeave > day){
            return true;
        }
        return false;
    }

    public boolean updateCasualLeave(Long employeeId, int day){
        Employee emp = new Employee();
        emp.setId(employeeId);

        LeavePolicy policy = leavePolicyRepo.findByEmployee(emp).orElse(null);

        if (policy == null){
            return false;
        }

        if (!checkCasualLeave(policy, day)){
            return false;
        }

        leavePolicyRepo.updateCasualSpent(emp,day);
        return true;
    }

    public boolean checkCasualLeave(LeavePolicy policy, int day){
        int remainingLeave = policy.getCasual() - policy.getCasualSpent();
        if (remainingLeave > day){
            return true;
        }
        return false;
    }

    public boolean updateUnpaidLeave(Long employeeId, int day){
        Employee emp = new Employee();
        emp.setId(employeeId);

        LeavePolicy policy = leavePolicyRepo.findByEmployee(emp).orElse(null);

        if (policy == null){
            return false;
        }

        leavePolicyRepo.updateUnpaidSpent(emp,day);
        return true;
    }


    public List<LeavePolicyDto> getAllLeave() {

        return leavePolicyRepo.findAll()
                .stream()
                .map(leavePolicyMapper::leavePolicyToDto)
                .toList();
    }
}
