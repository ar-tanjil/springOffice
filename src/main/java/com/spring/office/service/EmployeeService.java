package com.spring.office.service;

import com.spring.office.domain.Employee;
import com.spring.office.dto.*;
import com.spring.office.repo.EmployeeRepo;
import com.spring.office.service.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
//    Employee Repository
    private final EmployeeRepo empRepo;
//    Employee Class mapper
    private final EmployeeMapper empMapper;

//    Department Service Class
    private final DepartmentService depService;

//    Job Service Class
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

//    Get All Employee if Deleted is False. Return EmployeeTable
    public List<EmployeeTable> getAllEmployee() {
        Iterable<Employee> employees = this.empRepo.findAllByDeletedFalse();
        List<EmployeeTable> employeeList = new ArrayList<>();
        employees.forEach(emp -> employeeList.add(empMapper.employeeToTable(emp)));
        return employeeList;
    }

//    Get Employee by id. Return EmployeeDto
    public EmployeeDto getEmployeeById(Long id) {
        Optional<Employee> optEmp = empRepo.findByCustomId(id);

        return optEmp.map(empMapper::employeeToDto).orElse(null);

    }

// Save Employee, Accept EmployeeDto, Return EmployeeDto
    public EmployeeDto saveEmployee(EmployeeDto dto) {
            boolean vacancy = jobService.checkVacancyByJobId(dto.getJobId());
            if (vacancy){

                dto.setHireDate(LocalDate.now());
                jobService.reduceVacancy(1,dto.getJobId());
                Employee emp = empMapper.dtoToEmployee(dto);
                Employee saveEmp = empRepo.save(emp);

                return empMapper.employeeToDto(saveEmp);
            }
            return null;

    }


//    Update Employee , it's a patch update.
//    And if employee does not exist it save the employee
//    Needs refining, wants to use one method for save and update
//    Accept EmployeeDto, Return EmployeeDto.
    public EmployeeDto updateEmployee(Long id, EmployeeDto newDto) {

        Optional<Employee> optEmp = empRepo.findByCustomId(id);

        if (optEmp.isPresent()){
            Employee oldEmp = optEmp.get();
            Employee newEmp = empMapper.dtoToEmployee(newDto);
            Employee patchEmp = empMapper.updateMapper(newEmp, oldEmp);
            Employee updateEmp = empRepo.save(patchEmp);
            return empMapper.employeeToDto(updateEmp);
        }

        Employee emp = empMapper.dtoToEmployee(newDto);
        Employee updateEmp = empRepo.save(emp);
        return empMapper.employeeToDto(updateEmp);
    }

//    Delete By id if it exists. otherwise return false.
//    it's a soft delete, So u can call it terminate employee contract method.
    public Boolean deleteEmployeeById(Long id) {
        Optional<Employee> optEmp = empRepo.findByCustomId(id);
        if (optEmp.isPresent()) {
            Employee emp = optEmp.get();
            jobService.addVacancy(1,emp.getJob().getId());
            emp.setActive(false);
            emp.setDeleted(true);
            empRepo.save(emp);
            return true;
        }
        return false;
    }

}
