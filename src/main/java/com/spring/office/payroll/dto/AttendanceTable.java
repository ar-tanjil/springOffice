package com.spring.office.payroll.dto;


import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceTable {

    private List<Boolean> present;
    private String firstName;

}
