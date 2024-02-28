package com.spring.office.payroll.service;

import com.spring.office.employee.Employee;
import com.spring.office.payroll.domain.Additions;
import com.spring.office.payroll.dto.AdditionsDto;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.time.Year;
import java.time.YearMonth;

@Service
public class AdditionsMapper {

    public AdditionsDto additionsToDto(Additions att) {
        if (att.getEmployee() == null) {
            return null;
        }

        YearMonth ym = att.getPeriod();
        Year y = Year.of(ym.getYear());
        Month m = Month.of(ym.getMonthValue());

        return AdditionsDto.builder()
                .id(att.getId())
                .bonus(att.getBonus())
                .travelAllowance(att.getTravelAllowance())
                .month(m)
                .year(y.getValue())
                .employeeId(att.getEmployee().getId())
                .build();
    }


    public Additions dtoToAdditions(AdditionsDto dto){
        if (dto.getEmployeeId() == null){
            return null;
        }
        Employee emp = new Employee();
        emp.setId(dto.getEmployeeId());

        Month m = dto.getMonth();

        YearMonth ym = YearMonth.of(dto.getYear(), m);

       return Additions.builder()
               .id(dto.getId())
               .bonus(dto.getBonus())
               .period(ym)
               .travelAllowance(dto.getTravelAllowance())
               .employee(emp)
               .build();
    }

}
