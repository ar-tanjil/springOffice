package com.spring.office.domain.embaded;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class Qualification  {
    private String ssc;
    private LocalDate sscPassingYear;
    private String hsc;
    private LocalDate hscPassingYear;
    private String undergraduate;
    private LocalDate undergraduatePassingYear;
    private String postgraduate;
    private LocalDate postgraduatePassingYear;
}
