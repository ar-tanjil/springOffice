package com.spring.office.payroll.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaxDto {

    private Long id;
    private String title;
    private Double percentage;
    private Double minRange;
    private Double maxRange;

}
