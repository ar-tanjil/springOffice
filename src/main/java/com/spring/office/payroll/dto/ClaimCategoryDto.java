package com.spring.office.payroll.dto;

import com.spring.office.payroll.domain.ClaimType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClaimCategoryDto {

    private Long id;
    private String name;
    private String claimType;
}
