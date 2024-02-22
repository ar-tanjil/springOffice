package com.spring.office.service.mapper;

import com.spring.office.domain.Department;
import com.spring.office.domain.Employee;
import com.spring.office.domain.Job;
import com.spring.office.dto.EmployeeDto;
import org.springframework.stereotype.Service;

@Service
public class EmployeeMapper {

    public Employee dtoToEmployee(EmployeeDto dto){
        Employee emp = new Employee();

        if (dto.getId() != null) {
            emp.setId(dto.getId());
        }
        emp.setFirstName(dto.getFirstName());
        emp.setLastName(dto.getLastName());
        emp.setEmail(dto.getEmail());
        emp.setPhoneNumber(dto.getPhoneNumber());
        emp.setHireDate(dto.getHireDate());
        emp.setAddress(dto.getAddress());
        if(dto.getJob() != null){
            Job job = new Job();
            job.setId(dto.getJob());
            emp.setJob(job);
        }

        if(dto.getDepartment() != null){
            Department dep = new Department();
            dep.setId(dto.getDepartment());
            emp.setDepartment(dep);
        }

        return emp;
    }

    public EmployeeDto employeeToDto(Employee emp){
        EmployeeDto dto = new EmployeeDto();
        if (emp.getId() != null) {
            dto.setId(emp.getId());
        }

        dto.setFirstName(emp.getFirstName());
        dto.setLastName(emp.getLastName());
        dto.setEmail(emp.getEmail());
        dto.setPhoneNumber(emp.getPhoneNumber());
        dto.setHireDate(emp.getHireDate());
        dto.setAddress(emp.getAddress());
        if(emp.getJob() != null){
            dto.setJob(emp.getJob().getId());
        }
        if(emp.getDepartment() != null){
            dto.setDepartment(emp.getDepartment().getId());
        }

        return dto;
    }

}
