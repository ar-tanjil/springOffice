package com.spring.office.employee;

import com.spring.office.application.Application;
import com.spring.office.department.Department;
import com.spring.office.domain.embaded.Address;
import com.spring.office.domain.embaded.Qualification;
import com.spring.office.dto.table.EmployeeTable;
import com.spring.office.job.Job;
import org.springframework.stereotype.Service;

@Service
public class EmployeeMapper {

// A mapper. Accept EmployeeDto return Employee.
    public Employee dtoToEmployee(EmployeeDto dto) {

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

        if (dto.getApplicationId() != null){
            Application app = new Application();
            app.setId(dto.getApplicationId());
            emp.setApplication(app);
        }


        if(dto.getJobId() != null){
            Job job = new Job();
            job.setId(dto.getJobId());
            emp.setJob(job);
        }

        if (dto.getDepartmentId() != null){
            Department dep = new Department();
            dep.setId(dto.getDepartmentId());
            emp.setDepartment(dep);
        }

        return emp;
    }

//    A mapper, Accept Employee return EmployeeDto.
    public EmployeeDto employeeToDto(Employee emp) {
        EmployeeDto dto = new EmployeeDto();
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

        if(emp.getJob() != null){
            dto.setJobTitle(emp.getJob().getJobTitle());
            dto.setJobId(emp.getJob().getId());
        }

        if(emp.getDepartment() != null){
            dto.setDepartmentName(emp.getDepartment().getDepartmentName());
            dto.setDepartmentId(emp.getDepartment().getId());
        }

        if (emp.getSalary() != null){
            dto.setSalaryId(emp.getSalary().getId());
        }

        dto.setFirstName(emp.getFirstName());
        dto.setLastName(emp.getLastName());
        dto.setDob(emp.getDob());
        dto.setEmail(emp.getEmail());
        dto.setPhoneNumber(emp.getPhoneNumber());
        dto.setSeparationDate(emp.getSeparationDate());
        dto.setHireDate(emp.getHireDate());


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

        if(emp.getApplication() != null){
            dto.setApplicationId(emp.getApplication().getId());
        }

        return dto;

    }

//    Accept an Employee and return an EmployeeTable.
    public EmployeeTable employeeToTable(Employee employee) {
        EmployeeTable resEmp = new EmployeeTable();

        resEmp.setId(employee.getId());
        resEmp.setFirstName(employee.getFirstName());
        resEmp.setLastName(employee.getLastName());
        resEmp.setEmail(employee.getEmail());
        if (employee.getJob() != null) {
            resEmp.setJobTitle(employee.getJob().getJobTitle());
        }
        if (employee.getDepartment() != null) {
            resEmp.setDepartmentName(employee.getDepartment().getDepartmentName());
        }

        return resEmp;

    }

//    Accept tow employee, map it's value

    public Employee updateMapper(Employee newEmp, Employee oldEmp){

        if (newEmp.getFirstName() != null){
            oldEmp.setFirstName(newEmp.getFirstName());
        }

        if (newEmp.getLastName() != null){
            oldEmp.setLastName(newEmp.getLastName());
        }

        if (newEmp.getDob() != null){
            oldEmp.setDob(newEmp.getDob());
        }

        if(newEmp.getPhoneNumber() != null){
            oldEmp.setPhoneNumber(newEmp.getPhoneNumber());
        }

        if (newEmp.getEmail() != null){
            oldEmp.setEmail(newEmp.getEmail());
        }

        if (newEmp.getJob() != null){
            oldEmp.setJob(newEmp.getJob());
        }

        if (newEmp.getDepartment() != null){
            oldEmp.setDepartment(newEmp.getDepartment());
        }

        if (newEmp.getQualification() != null){
            oldEmp.setQualification(newEmp.getQualification());
        }

        return oldEmp;
    }

    public EmployeeShortDetails employeeToEmpSal(Employee employee){
        return new EmployeeShortDetails(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName()
        );
    }

}
