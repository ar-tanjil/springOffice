package com.spring.office.dto;

import lombok.Data;

@Data
public class DepartmentDto {
    private Long id;
    private String departmentName;
    private Long managerId;
    private String departmentDesc;
}
