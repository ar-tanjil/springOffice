package com.spring.office.dto;

public record ApplicantTableDto(
        Long id,
        String firstName,
        String jobTitle,
        String departmentName
) {
}
