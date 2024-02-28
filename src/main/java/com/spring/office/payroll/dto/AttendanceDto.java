package com.spring.office.payroll.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.NamedQueries;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceDto {
    private Long id;
    private LocalDate day;
    private LocalTime entryTime;
    private LocalTime leaveTime;
    private Long employeeId;
}
