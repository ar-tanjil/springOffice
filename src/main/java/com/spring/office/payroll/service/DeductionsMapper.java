package com.spring.office.payroll.service;

import com.spring.office.employee.Employee;
import com.spring.office.payroll.domain.Deductions;
import com.spring.office.payroll.dto.DeductionDto;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.time.YearMonth;

@Service
public class DeductionsMapper {

    public DeductionDto deductionToDto(Deductions deductions) {

        if (deductions.getEmployee() == null || deductions.getPeriod() == null) {
            return null;
        }


        YearMonth period = deductions.getPeriod();
        Integer year = period.getYear();
        Month month = period.getMonth();


        return DeductionDto.builder()
                .id(deductions.getId())
                .employeeId(deductions.getEmployee().getId())
                .tax(deductions.getTax())
                .loanPayment(deductions.getLoanPayment())
                .unpaidLeave(deductions.getUnpaidLeave())
                .month(month)
                .year(year)
                .build();

    }

    public Deductions dtoToDeduction(DeductionDto dto) {

        if (dto.getEmployeeId() == null) {
            return null;
        }

        YearMonth period = YearMonth.of(dto.getYear(), dto.getMonth());

        Employee employee = new Employee();
        employee.setId(dto.getEmployeeId());

        return Deductions.builder()
                .id(dto.getId())
                .employee(employee)
                .tax(dto.getTax())
                .loanPayment(dto.getLoanPayment())
                .unpaidLeave(dto.getUnpaidLeave())
                .period(period)
                .build();

    }

}
