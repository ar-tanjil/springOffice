package com.spring.office.anouncement.service;

import com.spring.office.anouncement.domain.Announcement;
import com.spring.office.anouncement.domain.AnnouncementTarget;
import com.spring.office.anouncement.dto.AnnouncementDto;
import com.spring.office.department.Department;
import com.spring.office.department.DepartmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
@RequiredArgsConstructor
public class AnnouncementMapper {

    private final DepartmentMapper departmentMapper;

    public AnnouncementDto announcementToDto(Announcement announcement){

        var dto = AnnouncementDto.builder()
                .id(announcement.getId())
                .date(announcement.getDate())
                .target(announcement.getTarget().toString())
                .title(announcement.getTitle())
                .subject(announcement.getSubject())
                .title(announcement.getTitle())
                .description(announcement.getDescription())
                .build();

        if (announcement.getTarget() == AnnouncementTarget.DEPARTMENT){
            dto.setDepartmentDto(departmentMapper.departmentToDto(announcement.getDepartment()));
        }

        return dto;
    }

    public Announcement dtoToAnnouncement(AnnouncementDto dto){

        var announcement = Announcement.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .subject(dto.getSubject())
                .date(dto.getDate())
                .target(AnnouncementTarget.valueOf(dto.getTarget().toUpperCase()))
                .description(dto.getDescription())
                .build();


        if (announcement.getTarget() == AnnouncementTarget.DEPARTMENT){
            Department  dep = new Department();
            dep.setId(dto.getDepartmentId());
            announcement.setDepartment(dep);
        }

        return announcement;
    }

}
