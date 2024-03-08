package com.spring.office.payroll.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TimePeriod {

    private LocalDate startDate;
    private LocalDate endDate;

}
