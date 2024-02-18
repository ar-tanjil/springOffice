package com.spring.office.service;

import com.spring.office.customUtil.DtoUtil;
import com.spring.office.domain.Employee;
import com.spring.office.dto.EmployeeDto;
import com.spring.office.repo.EmployeeRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private EmployeeRepo empRepo;

    private EmployeeService(EmployeeRepo employeeRepo) {
        this.empRepo = employeeRepo;
    }

    public List<EmployeeDto> getAll() {
        Iterable<Employee> employees = this.empRepo.findAll();
        List<EmployeeDto> employeeList = new ArrayList<>();
        employees.forEach(emp -> employeeList.add(DtoUtil.empToDto(emp)));
        return employeeList;
    }

    public EmployeeDto getById(Long id) {
        Optional<Employee> optEmp = empRepo.findById(id);

        if (optEmp.isPresent()) {
            return DtoUtil.empToDto(optEmp.get());
        }

        return null;
    }

    public EmployeeDto save(EmployeeDto dto){
        Employee emp = DtoUtil.dtoToEmp(dto);
        Employee saveEmp = empRepo.save(emp);
        return DtoUtil.empToDto(saveEmp);
    }


}
