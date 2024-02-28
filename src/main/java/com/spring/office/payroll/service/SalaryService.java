package com.spring.office.payroll.service;

import com.spring.office.employee.Employee;
import com.spring.office.payroll.domain.Salary;
import com.spring.office.payroll.dto.SalaryDto;
import com.spring.office.payroll.repo.SalaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SalaryService {

    private final SalaryRepository salaryRepository;
    private final SalaryMapper salaryMapper;

    public SalaryDto addSalary(SalaryDto dto ){
        Employee emp = new Employee();
        emp.setId(dto.getEmployeeId());

        Optional<Salary> salary = salaryRepository.findByEmployee(emp);

        if (salary.isEmpty()){
            Salary sal = salaryMapper.dtoToSalary(dto);
            var saveSal = salaryRepository.save(sal);
            return salaryMapper.salaryToDto(saveSal);
        }
            return null;
    }

    public SalaryDto updateSalary(SalaryDto dto){

        Employee emp = new Employee();
        emp.setId(dto.getEmployeeId());

        Optional<Salary> salary = salaryRepository.findByEmployee(emp);

        if (salary.isEmpty()){
            return null;
        }
        var swap = salaryMapper.swapSalary(salaryMapper.dtoToSalary(dto), salary.get());
        var updateSave = salaryRepository.save(swap);
        return salaryMapper.salaryToDto(updateSave);
    }


    public SalaryDto getSalaryByEmployee(Long id){
        Employee emp = new Employee();
        emp.setId(id);
        var getEmp = salaryRepository.findByEmployee(emp);
        if (getEmp.isEmpty()){
            return null;
        }
        return salaryMapper.salaryToDto(getEmp.get());
    }



}
