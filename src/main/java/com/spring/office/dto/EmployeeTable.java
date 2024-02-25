package com.spring.office.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class EmployeeTable {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String jobTitle;
    private String departmentName;
}
