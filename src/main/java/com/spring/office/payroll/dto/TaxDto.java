package com.spring.office.payroll.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaxDto implements Comparable<TaxDto>{

    private Long id;
    private String title;
    private Double percentage;
    private Double minRange;
    private Double maxRange;

    @Override
    public int compareTo(TaxDto o) {
        return this.minRange.compareTo(o.minRange);
    }
}
