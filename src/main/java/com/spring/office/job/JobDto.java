package com.spring.office.job;

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
    private  int minSalary;
    private  int maxSalary;
    private  int totalPost;
    private  int vacancy;
    private Long departmentId;
    private String departmentName;
}