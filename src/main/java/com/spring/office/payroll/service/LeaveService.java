package com.spring.office.payroll.service;

import com.spring.office.employee.Employee;
import com.spring.office.payroll.domain.Leave;
import com.spring.office.payroll.dto.LeaveDto;
import com.spring.office.payroll.repo.LeaveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LeaveService {


    private final LeaveRepository leaveRepository;
    private final LeaveMapper leaveMapper;
    public List<LeaveDto> getAllGrantedLeaveByEmpAndPeriod(Long empId, YearMonth period){
        LocalDate start = LocalDate.of(period.getYear(),
                period.getMonth(), 1);
        LocalDate end = LocalDate.of(period.getYear(),
                period.getMonth(), period.lengthOfMonth());

        Employee emp = new Employee();
        emp.setId(empId);

        List<Leave> listLeave = leaveRepository
                .findByEmployeeAndDayIsBetweenAndStatusTrue(emp, start, end);

        return listLeave.stream()
                .map(leaveMapper::leaveToDto).toList();
    }

    public Set<LocalDate> getLeaveGrantedDay(Long empId, YearMonth period){
        List<LeaveDto> list = getAllGrantedLeaveByEmpAndPeriod(empId,period);

        Set<LocalDate> daySet = new HashSet<>();
        list.forEach(listDat -> daySet.add(listDat.getDay()));
        return daySet;

    }

}
