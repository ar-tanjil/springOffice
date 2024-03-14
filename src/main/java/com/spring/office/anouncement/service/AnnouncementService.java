package com.spring.office.anouncement.service;

import com.spring.office.anouncement.domain.AnnouncementTarget;
import com.spring.office.anouncement.dto.AnnouncementDto;
import com.spring.office.anouncement.repo.AnnouncementRepo;
import com.spring.office.department.Department;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnnouncementService {

    private final AnnouncementMapper announcementMapper;
    private final AnnouncementRepo announcementRepo;


    public void saveAnnouncement(AnnouncementDto dto) {
        var announcement = announcementMapper.dtoToAnnouncement(dto);

        announcementRepo.save(announcement);

    }

    public AnnouncementDto getAnnouncementById(Long id) {
        var announcement = announcementRepo.findById(id)
                .orElse(null);
        if (announcement != null) {
            return announcementMapper.announcementToDto(announcement);
        }
        return null;
    }

    public List<AnnouncementDto> getAllAnnouncement() {
        return announcementRepo
                .findByTarget(AnnouncementTarget.ALL,Sort.by(Sort.Direction.DESC, "date"))
                .stream()
                .map(announcementMapper::announcementToDto)
                .toList();
    }

    public List<AnnouncementDto> getAllAnnouncementByDepartment(Long depId) {
        Department dep = new Department();
        dep.setId(depId);

        return announcementRepo
                .findByDepartment(dep,
                        Sort.by(Sort.Direction.DESC, "date"))
                .stream()
                .map(announcementMapper::announcementToDto)
                .toList();
    }

}
