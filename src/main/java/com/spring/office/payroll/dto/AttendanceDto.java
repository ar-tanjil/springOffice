package com.spring.office.payroll.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.NamedQueries;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceDto {
    private Long id;
    private LocalDate day;
    private LocalDateTime entryTime;
    private LocalDateTime leaveTime;
    private boolean present;
    private Long employeeId;
    private String employeeName;

}
