package com.spring.office.payroll.domain;

import com.spring.office.domain.BaseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalTime;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class OfficeRule extends BaseModel {

    @Enumerated(EnumType.STRING)
    private RulesEnum name;
    private LocalTime inTime;
    private LocalTime outTime;
    private int penalty;

}
