package com.spring.office.payroll.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private String status;
    private Long employeeId;
    private String employeeName;
}
