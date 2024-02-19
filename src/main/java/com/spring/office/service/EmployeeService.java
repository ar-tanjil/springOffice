package com.spring.office.service;

import com.spring.office.customUtil.DtoUtil;
import com.spring.office.domain.Employee;
import com.spring.office.domain.Job;
import com.spring.office.dto.DepartmentDto;
import com.spring.office.dto.EmployeeDto;
import com.spring.office.dto.JobDto;
import com.spring.office.repo.EmployeeRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private EmployeeRepo empRepo;
    private DepartmentService depService;
    private JobService jobService;

    public EmployeeService(EmployeeRepo employeeRepo,
                            DepartmentService depService,
                            JobService jobService) {
        this.empRepo = employeeRepo;
        this.jobService = jobService;
        this.depService = depService;
    }

    public List<EmployeeDto> getAll() {
        Iterable<Employee> employees = this.empRepo.findAllCustom();
        List<EmployeeDto> employeeList = new ArrayList<>();
        employees.forEach(emp -> employeeList.add(DtoUtil.empToDto(emp)));
        return employeeList;
    }

    public EmployeeDto getById(Long id) {
        Optional<Employee> optEmp = empRepo.findByCustomId(id);

        if (optEmp.isPresent()) {
            return DtoUtil.empToDto(optEmp.get());
        }

        return null;
    }


    public EmployeeDto save(EmployeeDto dto) {

        if(dto.getJob() != null){
            if(dto.getJob().getId() != null){
               JobDto job = jobService.getById(dto.getJob().getId());
                if (job != null){
                    dto.setJob(job);
                }
            }
        }


        if(dto.getDepartment() != null){
            if (dto.getDepartment().getId() != null){
               DepartmentDto dep = depService.getById(dto.getDepartment().getId());
                if (dep != null){
                    dto.setDepartment(dep);
                }
            }
        }




        Employee emp = DtoUtil.dtoToEmp(dto);
        Employee saveEmp = empRepo.save(emp);
        return DtoUtil.empToDto(saveEmp);
    }

    public EmployeeDto update(EmployeeDto dto) {
        Employee emp = DtoUtil.dtoToEmp(dto);
        Employee updateEmp = empRepo.save(emp);
        return DtoUtil.empToDto(updateEmp);
    }

    public Boolean delete(Long id) {
        Optional<Employee> optEmp = empRepo.findByCustomId(id);
        if (optEmp.isPresent()) {
            Employee emp = optEmp.get();
            emp.setActive(true);
            emp.setDeleted(true);
            empRepo.save(emp);
            return true;
        }
        return false;
    }


}
