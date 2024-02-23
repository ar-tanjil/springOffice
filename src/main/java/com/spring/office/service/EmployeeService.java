package com.spring.office.service;

import com.spring.office.domain.Employee;
import com.spring.office.dto.*;
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

    public List<EmpTableDto> getAll() {
        Iterable<Employee> employees = this.empRepo.findAllByDeletedFalse();
        List<EmpTableDto> employeeList = new ArrayList<>();
        employees.forEach(emp -> employeeList.add(empMapper.empToEmpResDto(emp)));
        return employeeList;
    }

    public EmpDetailsDto getById(Long id) {
        Optional<Employee> optEmp = empRepo.findByCustomId(id);

        return optEmp.map(empMapper::employeeToDto).orElse(null);

    }


    public EmpDetailsDto save(EmpDetailsDto dto) {



        Employee emp = empMapper.dtoToEmployee(dto);
        Employee saveEmp = empRepo.save(emp);

        return empMapper.employeeToDto(saveEmp);
    }

    public EmpDetailsDto update(Long id, EmpDetailsDto newDto) {

        Optional<Employee> optEmp = empRepo.findByCustomId(id);

        if (optEmp.isPresent()){
            EmpDetailsDto oldDto = empMapper.employeeToDto(optEmp.get());
            EmpDetailsDto patchDto = updateDto(newDto, oldDto);
            Employee emp = empMapper.dtoToEmployee(patchDto);
            Employee updateEmp = empRepo.save(emp);
            return empMapper.employeeToDto(updateEmp);
        }

        Employee emp = empMapper.dtoToEmployee(newDto);
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

    private DepartReceiveDto getDep(long id){
        return depService.getById(id);
    }

    private EmpDetailsDto updateDto(EmpDetailsDto newApp, EmpDetailsDto oldApp){

        if (newApp.getFirstName() != null) {
            oldApp.setFirstName(newApp.getFirstName());
        }
        if (newApp.getLastName() != null) {
            oldApp.setLastName(newApp.getLastName());
        }
        if (newApp.getEmail() != null) {
            oldApp.setEmail(newApp.getEmail());
        }
        if (newApp.getPhoneNumber() != null) {
            oldApp.setPhoneNumber(newApp.getPhoneNumber());
        }
        if (newApp.getDepartmentId() != null) {
            oldApp.setDepartmentId(newApp.getDepartmentId());
        }
        if (newApp.getDob() != null) {
            oldApp.setDob(newApp.getDob());
        }
        if (newApp.getSsc() != null) {
            oldApp.setSsc(newApp.getSsc());
        }
        if (newApp.getHsc() != null) {
            oldApp.setHsc(newApp.getHsc());
        }
        if (newApp.getUndergraduate() != null) {
            oldApp.setUndergraduate(newApp.getUndergraduate());
        }
        if (newApp.getPostgraduate() != null) {
            oldApp.setPostgraduate(newApp.getPostgraduate());
        }
        if (newApp.getSscPassingYear() != null) {
            oldApp.setSscPassingYear(newApp.getSscPassingYear());
        }
        if (newApp.getHscPassingYear() != null) {
            oldApp.setHscPassingYear(newApp.getHscPassingYear());
        }
        if (newApp.getUndergraduatePassingYear() != null) {
            oldApp.setUndergraduatePassingYear(newApp.getUndergraduatePassingYear());
        }
        if (newApp.getPostgraduatePassingYear() != null) {
            oldApp.setPostgraduatePassingYear(newApp.getPostgraduatePassingYear());
        }
        if (newApp.getZipCode() != null) {
            oldApp.setZipCode(newApp.getZipCode());
        }
        if (newApp.getRoadNo() != null) {
            oldApp.setRoadNo(newApp.getRoadNo());
        }

        if (newApp.getCity() != null) {
            oldApp.setCity(newApp.getCity());
        }
        if (newApp.getCountry() != null) {
            oldApp.setCountry(newApp.getCountry());
        }
        return oldApp;

    }

}
