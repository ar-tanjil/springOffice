package com.spring.office.payroll.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OfficeRuleDto {

    private Long id;
    private String name;
    private String inTime;
    private String outTime;
    private Integer penalty;

}
