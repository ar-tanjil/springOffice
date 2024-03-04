package com.spring.office.payroll.dto;

import lombok.*;
import org.hibernate.tool.schema.extract.spi.ExtractionContext;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class SalaryDto {

    private Long id;
    private Double basic;
    private Double medicalAllowance;
    private Double providentFund;
    private Double travelAllowance;
    private Integer bonus;
    private Double travel;
    private Double bonusAmount;
    private Double provident;
    private Double medical;
    private Double loan;
    private Long employeeId;

}
