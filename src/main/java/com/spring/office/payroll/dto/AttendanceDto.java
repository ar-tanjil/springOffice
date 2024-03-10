package com.spring.office.payroll.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spring.office.payroll.domain.AttendanceStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    private String checkIn;
    private String checkOut;
    private AttendanceStatus checkInStatus;
    private AttendanceStatus checkOutStatus;
    private Long employeeId;
    private String employeeName;

}
