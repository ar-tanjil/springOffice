package com.spring.office.payroll.dto;

import com.spring.office.dto.table.EmployeeTable;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClaimDto {
    private Long id;
    private String title;
    private String claimStatus;
    private Double amount;
    private LocalDate date;
    private ClaimCategoryDto claimCategory;
    private EmployeeTable employee;
    private Long employeeId;
    private Long categoryId;
}
