package com.spring.office.service;

import com.spring.office.domain.Employee;
import com.spring.office.dto.DepartmentDto;
import com.spring.office.dto.EmpResponseDto;
import com.spring.office.dto.EmpReceiveDto;
import com.spring.office.dto.JobDto;
import com.spring.office.repo.EmployeeRepo;
import com.spring.office.service.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepo empRepo;
    private final EmployeeMapper empMapper;
    private final DepartmentService depService;
    private final JobService jobService;



    public EmployeeService(
            EmployeeRepo employeeRepo,
            EmployeeMapper mapper,
            DepartmentService depService,
            JobService jobService) {
        this.empRepo = employeeRepo;
        this.empMapper = mapper;
        this.depService = depService;
        this.jobService = jobService;
    }

    public List<EmpResponseDto> getAll() {
        Iterable<Employee> employees = this.empRepo.findAllCustom();
        List<EmpResponseDto> employeeList = new ArrayList<>();
        employees.forEach(emp -> employeeList.add(empMapper.empToEmpResDto(emp)));
        return employeeList;
    }

    public EmpReceiveDto getById(Long id) {
        Optional<Employee> optEmp = empRepo.findByCustomId(id);

        return optEmp.map(empMapper::employeeToDto).orElse(null);

    }


    public EmpReceiveDto save(EmpReceiveDto dto) {

        Employee emp = empMapper.dtoToEmployee(dto);
        Employee saveEmp = empRepo.save(emp);
        return empMapper.employeeToDto(saveEmp);
    }

    public EmpReceiveDto update(EmpReceiveDto dto) {

        Employee emp = empMapper.dtoToEmployee(dto);
        Employee updateEmp = empRepo.save(emp);
        return empMapper.employeeToDto(updateEmp);
    }

    public Boolean delete(Long id) {
        Optional<Employee> optEmp = empRepo.findByCustomId(id);
        if (optEmp.isPresent()) {
            Employee emp = optEmp.get();
            emp.setActive(false);
            emp.setDeleted(true);
            empRepo.save(emp);
            return true;
        }
        return false;
    }

    private JobDto getJob(long id){
        return jobService.getById(id);
    }

    private DepartmentDto getDep(long id){
        return depService.getById(id);
    }


}
