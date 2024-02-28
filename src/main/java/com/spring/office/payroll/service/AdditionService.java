package com.spring.office.payroll.service;

import com.spring.office.employee.Employee;
import com.spring.office.payroll.domain.Additions;
import com.spring.office.payroll.dto.AdditionsAddDto;
import com.spring.office.payroll.dto.AdditionsDto;
import com.spring.office.payroll.repo.AdditionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.time.YearMonth;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdditionService {

    private final AdditionsRepository additionsRepository;
    private final AdditionsMapper additionsMapper;

    public AdditionsDto addAdditionByPeriod(
            Long empId, Integer year, Integer month, AdditionsAddDto addDto
    ) {
        YearMonth period = YearMonth.of(year, month);
        Employee employee = new Employee();
        employee.setId(empId);
        AdditionsDto dto = AdditionsDto.builder()
                .bonus(addDto.bonus())
                .travelAllowance(addDto.travelAllowance())
                .employeeId(empId)
                .year(year)
                .month(Month.of(month))
                .build();

        Optional<Additions> optionalAdditions = additionsRepository
                .findByEmployeeAndPeriod(employee, period);

        if (optionalAdditions.isPresent()) {
            return null;
        }

        var saveAdd = additionsRepository.save(additionsMapper.dtoToAdditions(dto));

        return additionsMapper.additionsToDto(saveAdd);

    }


    public AdditionsDto getAdditionsByPeriod(Long empId, Integer year, Integer month){
        YearMonth period = YearMonth.of(year,month);
        Employee employee = new Employee();
        employee.setId(empId);
        return additionsRepository.findByEmployeeAndPeriod(employee,period)
                .map(additionsMapper::additionsToDto).orElse(null);
    }


}
