package com.spring.office.payroll.domain;

import com.spring.office.domain.BaseModel;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Holiday extends BaseModel {

    private LocalDate day;
    private String reason;

}
