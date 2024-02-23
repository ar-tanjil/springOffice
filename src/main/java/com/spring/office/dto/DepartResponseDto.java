package com.spring.office.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartResponseDto {

    private Long id;
    private String departmentName;
    private Long managerId;
    private String departmentDesc;
}
