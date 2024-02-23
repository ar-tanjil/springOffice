package com.spring.office.service.mapper;

import com.spring.office.domain.*;
import com.spring.office.domain.embaded.Address;
import com.spring.office.domain.embaded.Qualification;
import com.spring.office.dto.EmpTableDto;
import com.spring.office.dto.EmpDetailsDto;
import org.springframework.stereotype.Service;

@Service
public class EmployeeMapper {

    public Employee dtoToEmployee(EmpDetailsDto dto) {

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

    public EmpDetailsDto employeeToDto(Employee emp) {
        EmpDetailsDto dto = new EmpDetailsDto();
        Address address = emp.getAddress();
        if (address == null) {
            address = new Address();
        }

        Qualification qualification = emp.getQualification();
        if (qualification == null) {
            qualification = new Qualification();
        }

        if(emp.getId() != null){
            dto.setId(emp.getId());
        }
        dto.setFirstName(emp.getFirstName());
        dto.setLastName(emp.getLastName());
        dto.setDob(emp.getDob());
        dto.setEmail(emp.getEmail());
        dto.setPhoneNumber(emp.getPhoneNumber());
        dto.setSsc(qualification.getSsc());
        dto.setHsc(qualification.getHsc());
        dto.setPostgraduate(qualification.getPostgraduate());
        dto.setUndergraduate(qualification.getUndergraduate());
        dto.setSscPassingYear(qualification.getSscPassingYear());
        dto.setHscPassingYear(qualification.getHscPassingYear());
        dto.setUndergraduatePassingYear(qualification.getUndergraduatePassingYear());
        dto.setPostgraduatePassingYear(qualification.getPostgraduatePassingYear());
        dto.setZipCode(address.getZipCode());
        dto.setRoadNo(address.getRoadNo());
        dto.setCity(address.getCity());
        dto.setCountry(address.getCountry());
        if(emp.getJob() != null){
            dto.setJob(emp.getJob().getId());
        }
        if(emp.getApplication() != null){
            dto.setApplication(emp.getApplication().getId());
        }

        return dto;

    }

    public EmpTableDto empToEmpResDto(Employee employee) {
        EmpTableDto resEmp = new EmpTableDto();

        resEmp.setId(employee.getId());
        resEmp.setFirstName(employee.getFirstName());
        resEmp.setLastName(employee.getLastName());
        resEmp.setEmail(employee.getEmail());
        resEmp.setPhoneNumber(employee.getPhoneNumber());

        if (employee.getJob() != null) {
            resEmp.setJobTitle(employee.getJob().getJobTitle());
        }
        if (employee.getDepartment() != null) {
            resEmp.setDepartmentName(employee.getDepartment().getDepartmentName());
        }

        return resEmp;

    }

}
