package com.spring.office.payroll.service;

import com.spring.office.department.Department;
import com.spring.office.employee.Employee;
import com.spring.office.job.Job;
import com.spring.office.payroll.domain.Salary;
import com.spring.office.payroll.dto.SalaryDto;
import com.spring.office.payroll.dto.SalaryTable;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
public class SalaryMapper {


    public Salary dtoToSalary(SalaryDto dto){

        if (dto.getEmployeeId() == null){
            return null;
        }

        Employee emp = new Employee();
        emp.setId(dto.getEmployeeId());

        return Salary.builder()
                .id(dto.getId())
                .basic(dto.getBasic())
                .medicalAllowance(dto.getMedicalAllowance())
                .providentFund(dto.getProvidentFund())
                .employee(emp)
                .build();
    }


    public SalaryDto salaryToDto(Salary salary){
        if (salary.getEmployee() == null){
            return null;
        }

        return SalaryDto.builder()
                .id(salary.getId())
                .employeeId(salary.getEmployee().getId())
                .basic(salary.getBasic())
                .medicalAllowance(salary.getMedicalAllowance())
                .providentFund(salary.getProvidentFund())
                .build();
    }

    public Salary swapSalary(SalaryDto newSal, Salary oldSal){
        if (newSal.getBasic() !=null){
            oldSal.setBasic(newSal.getBasic());
        }

        if (newSal.getProvidentFund() != null){
            oldSal.setProvidentFund(newSal.getProvidentFund());
        }

        if (newSal.getMedicalAllowance() != null){
            oldSal.setMedicalAllowance(newSal.getMedicalAllowance());
        }
        return oldSal;
    }

    public SalaryTable salaryToTable(Salary salary){
        Employee employee = salary.getEmployee();

        if (employee == null){
            return null;
        }
        Job job = employee.getJob();
        Department department = employee.getDepartment();

        if (job == null){
            job = new Job();
        }

        if (department == null){
            department = new Department();
        }


        return new SalaryTable(
                salary.getId(),
                salary.getBasic(),
                salary.getMedicalAllowance(),
                salary.getProvidentFund(),
                employee.getId(),
                employee.getFirstName(),
                job.getJobTitle(),
                department.getDepartmentName()

        );


    }

}
