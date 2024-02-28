package com.spring.office.payroll.dto;

import java.time.LocalDate;

public record DateStartEnd(
        LocalDate start,
        LocalDate end
) {}
