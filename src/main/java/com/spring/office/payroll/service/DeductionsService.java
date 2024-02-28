package com.spring.office.payroll.service;

import com.spring.office.employee.Employee;
import com.spring.office.payroll.domain.Deductions;
import com.spring.office.payroll.dto.DeductionAddDto;
import com.spring.office.payroll.dto.DeductionDto;
import com.spring.office.payroll.repo.DeductionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.time.YearMonth;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeductionsService {

    private final DeductionsRepository deductionsRepository;
    private final DeductionsMapper deductionsMapper;
    private final TaxService taxService;
    public DeductionDto saveDeductions(Long empId,Integer year, Integer month, DeductionAddDto add) {

        DeductionDto dto = DeductionDto.builder()
                .employeeId(empId)
                .loanPayment(add.loanPayment())
                .unpaidLeave(add.unpaidLeave())
                .year(year)
                .month(Month.of(month))
                .build();

        Employee emp = new Employee();
        emp.setId(empId);

        Optional<Deductions> optionalDeductions = deductionsRepository
                .findByEmployeeAndPeriod(emp, YearMonth.of(year, month));

        if (optionalDeductions.isPresent()) {
            return null;
        }

        Deductions deductions = deductionsRepository
                .save(deductionsMapper.dtoToDeduction(dto));
        return deductionsMapper.deductionToDto(deductions);
    }


    public DeductionDto getByEmpIdAndPeriod(Long empId, Integer year, Integer month){
        Employee emp = new Employee();
        emp.setId(empId);
        YearMonth period = YearMonth.of(year,month);

        var deduction = deductionsRepository.findByEmployeeAndPeriod(emp,period);
        return deduction.map(deductionsMapper::deductionToDto).orElse(null);

    }

    public void updateTax(double tax, YearMonth period, Employee emp){
        deductionsRepository.updateTax(tax,period,emp);
    }


}
