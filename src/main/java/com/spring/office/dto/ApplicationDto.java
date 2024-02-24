package com.spring.office.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ApplicationDto {

    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String email;
    private String phoneNumber;
    private String ssc;
    private LocalDate sscPassingYear;
    private String hsc;
    private LocalDate hscPassingYear;
    private String undergraduate;
    private LocalDate undergraduatePassingYear;
    private String postgraduate;
    private LocalDate postgraduatePassingYear;
    private String zipCode;
    private String roadNo;
    private String city;
    private String country;
    private Long jobId;
    private String reference;
    private String jobTitle;
    private String departmentName;

}
