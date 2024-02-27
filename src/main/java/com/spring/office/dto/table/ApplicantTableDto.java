package com.spring.office.dto.table;

public record ApplicantTableDto(
        Long id,
        String firstName,
        String jobTitle,
        String departmentName
) {
}
