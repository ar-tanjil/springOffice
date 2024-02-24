package com.spring.office.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class EmpTableDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String jobTitle;
    private String departmentName;
}
