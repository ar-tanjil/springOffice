package com.spring.office.payroll.dto;

import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OfficeDaysDto {

    private Long id;
    private String day;
    private LocalTime startTime;
    private LocalTime endTime;
    private String status;

}
