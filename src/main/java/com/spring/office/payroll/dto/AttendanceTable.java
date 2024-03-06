package com.spring.office.payroll.dto;


import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceTable {

    private boolean[] present;
    private String firstName;

}
