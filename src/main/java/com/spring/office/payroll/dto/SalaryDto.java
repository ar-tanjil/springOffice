package com.spring.office.payroll.dto;

import com.spring.office.dto.table.EmployeeTable;
import com.spring.office.employee.Employee;
import lombok.*;
import org.hibernate.tool.schema.extract.spi.ExtractionContext;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class SalaryDto {

    private Long id;
    private Double basic;
    private Double medicalAllowance;
    private Double providentFund;
    private Double travelAllowance;
    private Double provident;
    private Double medical;
    private Double travel;
    private Double loan;
    private Long employeeId;
    private EmployeeTable employeeTable;

}
