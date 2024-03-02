package com.spring.office.payroll.dto;

import lombok.*;
import org.springframework.stereotype.Service;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeNameAndId {
   private Long id;
   private String firstName;
}
