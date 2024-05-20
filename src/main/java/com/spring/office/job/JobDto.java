package com.spring.office.job;

import com.spring.office.department.DepartmentDto;
import com.spring.office.dto.table.EmployeeTable;
import com.spring.office.employee.EmployeeShortDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private List<String> requirements;
    private Long departmentId;
    private DepartmentDto departmentDto;
}