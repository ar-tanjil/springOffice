package com.spring.office.payroll.dto;

import java.time.LocalDate;

public record HolidayDto (
        LocalDate day,
        String reason
){
}
