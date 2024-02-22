package com.spring.office.service.mapper;

import com.spring.office.domain.*;
import com.spring.office.domain.embaded.Address;
import com.spring.office.domain.embaded.Qualification;
import com.spring.office.dto.EmpResponseDto;
import com.spring.office.dto.EmpReceiveDto;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class EmployeeMapper {

    public Employee dtoToEmployee(EmpReceiveDto dto) {

        Address address = new Address(
                dto.getRoadNo(),
                dto.getZipCode(),
                dto.getCity(),
                dto.getCountry()
        );

        Qualification qualification = new Qualification(
                dto.getSsc(),
                dto.getSscPassingYear(),
                dto.getHsc(),
                dto.getHscPassingYear(),
                dto.getUndergraduate(),
                dto.getUndergraduatePassingYear(),
                dto.getPostgraduate(),
                dto.getPostgraduatePassingYear()
        );



        Employee emp = new Employee();
        if (dto.getId() != null){
            emp.setId(dto.getId());
        }
        emp.setFirstName(dto.getFirstName());
        emp.setLastName(dto.getLastName());
        emp.setDob(dto.getDob());
        emp.setEmail(dto.getEmail());
        emp.setPhoneNumber(dto.getPhoneNumber());
        emp.setQualification(qualification);
        emp.setAddress(address);
        emp.setHireDate(dto.getHireDate());
        emp.setSeparationDate(dto.getSeparationDate());

        if (dto.getApplication() != null){
            Application app = new Application();
            app.setId(dto.getApplication());
            emp.setApplication(app);
        }


        if(dto.getJob() != null){
            Job job = new Job();
            job.setId(dto.getJob());
            emp.setJob(job);
        }

        if (dto.getDepartment() != null){
            Department dep = new Department();
            dep.setId(dto.getDepartment());
            emp.setDepartment(dep);
        }

        return emp;
    }

    public EmpReceiveDto employeeToDto(Employee emp) {
        EmpReceiveDto dto = new EmpReceiveDto();
        if (emp.getId() != null) {
            dto.setId(emp.getId());
        }

        dto.setFirstName(emp.getFirstName());
        dto.setLastName(emp.getLastName());
        dto.setEmail(emp.getEmail());
        dto.setPhoneNumber(emp.getPhoneNumber());
        dto.setHireDate(emp.getHireDate());
        if (emp.getJob() != null) {
            dto.setJob(emp.getJob().getId());
        }
        if (emp.getDepartment() != null) {
            dto.setDepartment(emp.getDepartment().getId());
        }

        return dto;
    }

    public EmpResponseDto empToEmpResDto(Employee employee) {
        EmpResponseDto resEmp = new EmpResponseDto();

        resEmp.setId(employee.getId());
        resEmp.setFirstName(employee.getFirstName());
        resEmp.setLastName(employee.getLastName());
        resEmp.setEmail(employee.getEmail());
        resEmp.setPhoneNumber(employee.getPhoneNumber());

        if (employee.getJob() != null) {
            System.out.println(employee.getJob());
            resEmp.setJobTitle(employee.getJob().getJobTitle());
        }
        if (employee.getDepartment() != null) {
            resEmp.setDepartmentName(employee.getDepartment().getDepartmentName());
        }

        return resEmp;

    }

}
