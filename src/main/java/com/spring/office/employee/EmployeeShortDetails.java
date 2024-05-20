package com.spring.office.employee;

public record EmployeeShortDetails(
        Long id,
        String firstName,
        String lastName,
        String email
) {
}
