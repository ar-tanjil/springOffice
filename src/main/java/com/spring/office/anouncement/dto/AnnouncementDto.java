package com.spring.office.anouncement.dto;

import com.spring.office.department.DepartmentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnnouncementDto {

    private Long id;
    private String title;
    private String target;
    private LocalDate date;
    private String subject;
    private String description;
    private Long departmentId;
    private DepartmentDto departmentDto;

}
