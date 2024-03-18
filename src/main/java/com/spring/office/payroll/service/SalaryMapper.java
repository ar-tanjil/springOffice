package com.spring.office.payroll.service;

import com.spring.office.employee.Employee;
import com.spring.office.employee.EmployeeMapper;
import com.spring.office.payroll.domain.Salary;
import com.spring.office.payroll.dto.SalaryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SalaryMapper {

    private final EmployeeMapper employeeMapper;

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
                .travelAllowance(dto.getTravelAllowance())
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
                .loan(salary.getLoan())
                .epf(salary.getEpf())
                .travelAllowance(salary.getTravelAllowance())
                .travel(calculateTravel(salary.getBasic(), salary.getTravelAllowance()))
                .medical(calculateMedical(salary.getBasic(), salary.getMedicalAllowance()))
                .provident(calculateProvident(salary.getBasic(), salary.getProvidentFund()))
                .employeeTable(employeeMapper.employeeToTable(salary.getEmployee()))
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




    private Double calculateMedical(Double salary, Double ma){
        if(ma > 0){
            return salary * ma / 100;
        }
        return 0D;
    }

    private Double calculateProvident(Double salary, Double pf){
        if (pf > 0){
            return salary * pf / 100;
        }
        return 0D;
    }

    private Double calculateTravel(double salary, double ta){
        if (ta > 0){
            return salary * ta / 100;
        }
        return 0D;
    }


}
