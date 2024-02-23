package com.spring.office.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartReceiveDto {
    private Long id;
    private String departmentName;
    private Long managerId;
    private String departmentDesc;
    private Long employees;
    private Long job;
}
