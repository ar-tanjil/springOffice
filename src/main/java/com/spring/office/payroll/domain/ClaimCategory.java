package com.spring.office.payroll.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.office.domain.BaseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class ClaimCategory extends BaseModel {

    private String name;

    @Enumerated(EnumType.STRING)
    private ClaimType claimType;

    @OneToMany(mappedBy = "claimCategory")
    @JsonIgnore
    @JsonBackReference
    private Set<Claim> claims;


}
