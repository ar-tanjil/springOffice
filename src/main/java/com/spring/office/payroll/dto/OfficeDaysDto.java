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
    private String startTime;
    private String endTime;
    private String status;

}
