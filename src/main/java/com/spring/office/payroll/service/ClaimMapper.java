package com.spring.office.payroll.service;

import com.spring.office.employee.Employee;
import com.spring.office.employee.EmployeeMapper;
import com.spring.office.payroll.domain.Claim;
import com.spring.office.payroll.domain.ClaimCategory;
import com.spring.office.payroll.domain.ClaimStatus;
import com.spring.office.payroll.dto.ClaimDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClaimMapper {

    private final ClaimCategoryMapper claimCategoryMapper;
    private final EmployeeMapper employeeMapper;
    public ClaimDto claimToDto(Claim claim){

        return ClaimDto.builder()
                .id(claim.getId())
                .title(claim.getTitle())
                .amount(claim.getAmount())
                .claimStatus(claim.getClaimStatus().name())
                .date(claim.getDate())
                .claimCategory(claimCategoryMapper.claimCategoryToDto(claim.getClaimCategory()))
                .employee(employeeMapper.employeeToTable(claim.getEmployee()))
                .build();

    }

    public Claim dtoToClaim(ClaimDto dto){

        Employee employee = new Employee();
        employee.setId(dto.getEmployeeId());

        ClaimCategory category = new ClaimCategory();
        category.setId(dto.getCategoryId());


        return Claim.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .amount(dto.getAmount())
                .claimStatus(ClaimStatus.valueOf(dto.getClaimStatus()))
                .date(dto.getDate())
                .claimCategory(category)
                .employee(employee)
                .build();
    }

}
