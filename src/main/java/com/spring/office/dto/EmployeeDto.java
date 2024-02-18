package com.spring.office.dto;



import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate hireDate;
    private JobDto job;

    private DepartmentDto department;
    private String address;


}
