package com.spring.office.payroll.domain;


import com.spring.office.domain.BaseModel;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Tax extends BaseModel {

    private String title;
    private float percentage;
    private float minRange;
    private float maxRange;
}
