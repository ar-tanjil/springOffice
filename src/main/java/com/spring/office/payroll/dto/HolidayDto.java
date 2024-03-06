package com.spring.office.payroll.dto;

import java.time.LocalDate;

public record HolidayDto (
        Long id,
        LocalDate day,
        String reason
){
}
