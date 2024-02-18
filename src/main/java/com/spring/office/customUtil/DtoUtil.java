package com.spring.office.customUtil;


import com.spring.office.domain.Department;
import com.spring.office.domain.Employee;
import com.spring.office.domain.Job;
import com.spring.office.dto.DepartmentDto;
import com.spring.office.dto.EmployeeDto;
import com.spring.office.dto.JobDto;

public class DtoUtil {


    public static Employee dtoToEmp(EmployeeDto dto) {
        Employee emp = new Employee();

        if (dto.getId() != null) {
            emp.setId(dto.getId());
        }

        emp.setFirstName(dto.getFirstName());
        emp.setLastName(dto.getLastName());
        emp.setEmail(dto.getEmail());
        emp.setPhoneNumber(dto.getPhoneNumber());
        emp.setAddress(dto.getAddress());
        emp.setJob(dtoToJob(dto.getJob()));
        emp.setDepartment(dtoToDep(dto.getDepartment()));

        return emp;
    }

    public static Job dtoToJob(JobDto dto) {
        Job job = new Job();

        if (dto.getId() != null) {
            job.setId(dto.getId());
        }

        job.setJobTitle(dto.getJobTitle());
        job.setMaxSalary(dto.getMaxSalary());
        job.setMinSalary(dto.getMinSalary());
        return job;
    }

    public static Department dtoToDep(DepartmentDto dto) {
        Department dep = new Department();
        if (dto.getId() != null) {
            dep.setId(dto.getId());
        }
        dep.setDepartmentName(dto.getDepartmentName());
        dep.setDepartmentDesc(dto.getDepartmentDesc());
        dep.setManagerId(dto.getManagerId());
        return dep;
    }

    public static EmployeeDto empToDto(Employee emp) {
        EmployeeDto dto = new EmployeeDto();
        if (emp.getId() != null) {
            dto.setId(emp.getId());
        }

        dto.setFirstName(emp.getFirstName());
        dto.setLastName(emp.getLastName());
        dto.setEmail(emp.getEmail());
        dto.setPhoneNumber(emp.getPhoneNumber());
        dto.setHireDate(emp.getHireDate());
        dto.setJob(jobToDto(emp.getJob()));
        dto.setDepartment(depToDto(emp.getDepartment()));

        return dto;
    }

    public static JobDto jobToDto(Job job) {
        JobDto dto = new JobDto();

        if (job.getId() != null) {
            dto.setId(job.getId());
        }

        dto.setJobTitle(job.getJobTitle());
        dto.setMinSalary(job.getMinSalary());
        dto.setMaxSalary(job.getMaxSalary());
        return dto;
    }

    public static DepartmentDto depToDto(Department dep) {
        DepartmentDto dto = new DepartmentDto();
        if (dep.getId() != null) {
            dto.setId(dep.getId());
        }
        dto.setDepartmentName(dep.getDepartmentName());
        dto.setDepartmentDesc(dep.getDepartmentDesc());
        dto.setManagerId(dep.getManagerId());
        return dto;
    }


}
