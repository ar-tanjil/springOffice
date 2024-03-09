package com.spring.office.payroll.dto;


import com.spring.office.dto.table.EmployeeTable;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeavePolicyDto {

    private Long id;
    private Integer medical;
    private Integer casual;
    private Integer medicalSpent;
    private Integer casualSpent;
    private Integer unpaidSpent;
    private EmployeeTable employee;
    private Long employeeId;
}
