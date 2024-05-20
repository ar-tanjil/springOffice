package com.spring.office.anouncement.domain;

import com.spring.office.department.Department;
import com.spring.office.domain.BaseModel;
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
public class Announcement extends BaseModel {

    private String title;

    @Enumerated(EnumType.STRING)
    private AnnouncementTarget target;
    private LocalDate date;
    private String subject;
    private String description;

    @OneToOne
    @JoinColumn(name = "department_id")
    private Department department;

}
