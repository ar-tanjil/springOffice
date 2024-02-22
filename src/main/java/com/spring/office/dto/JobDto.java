package com.spring.office.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobDto {
    private Long id;
    private String jobTitle;
    private Integer minSalary;
    private Integer maxSalary;
}
