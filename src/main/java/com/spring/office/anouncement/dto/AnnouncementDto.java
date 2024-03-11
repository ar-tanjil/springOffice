package com.spring.office.anouncement.dto;

import com.spring.office.anouncement.domain.AnnouncementTarget;
import com.spring.office.department.Department;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.time.LocalDate;

public class AnnouncementDto {

    private Long id;
    private String title;
    private String target;
    private LocalDate date;
    private String subject;
    private String description;
    private Long departmentId;

}
