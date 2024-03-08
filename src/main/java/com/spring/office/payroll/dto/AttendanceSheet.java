package com.spring.office.payroll.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceSheet {

    private boolean[] present;
    private String firstName;

}
