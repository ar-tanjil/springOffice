package com.spring.office.dto;

import lombok.Data;

@Data
public class JobDto {
    private Long id;
    private String jobTitle;
    private Integer minSalary;
    private Integer maxSalary;
}
