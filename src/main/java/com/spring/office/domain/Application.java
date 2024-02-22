package com.spring.office.domain;


import com.spring.office.domain.embaded.Address;
import com.spring.office.domain.embaded.Qualification;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Application extends BaseModel{

    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String email;
    private String phoneNumber;
    @Embedded
    private Qualification qualifications;
    @Embedded
    private Address address;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;
    public String reference;

}
