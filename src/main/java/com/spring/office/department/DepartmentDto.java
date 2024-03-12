package com.spring.office.department;

import com.spring.office.dto.table.EmployeeTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {
    private Long id;
    private String departmentName;
    private Long managerId;
    private EmployeeTable manager;
    private String departmentDesc;
}
