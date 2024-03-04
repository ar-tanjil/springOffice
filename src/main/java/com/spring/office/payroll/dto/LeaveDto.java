package com.spring.office.payroll.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeaveDto {

    private Long id;
    private LocalDate day;
    private String type;
    private String reason;
    private boolean status;
    private Long employeeId;
}
