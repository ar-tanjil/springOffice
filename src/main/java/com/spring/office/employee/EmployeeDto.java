package com.spring.office.employee;



import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate hireDate;
    private LocalDate separationDate;
    private LocalDate dob;
    @NotNull
    private Long jobId;
    private Long departmentId;
    private String zipCode;
    private String roadNo;
    private String city;
    private String country;
    private String ssc;
    private LocalDate sscPassingYear;
    private String hsc;
    private LocalDate hscPassingYear;
    private String undergraduate;
    private LocalDate undergraduatePassingYear;
    private String postgraduate;
    private LocalDate postgraduatePassingYear;
    private Long applicationId;
    private String jobTitle;
    private String departmentName;
}
