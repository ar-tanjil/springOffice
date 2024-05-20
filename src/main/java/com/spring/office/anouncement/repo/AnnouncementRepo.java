package com.spring.office.anouncement.repo;


import com.spring.office.anouncement.domain.Announcement;
import com.spring.office.anouncement.domain.AnnouncementTarget;
import com.spring.office.department.Department;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface AnnouncementRepo extends JpaRepository<Announcement, Long> {
    List<Announcement> findByDepartment(Department department,Sort date);

    List<Announcement> findByTarget(AnnouncementTarget announcementTarget, Sort date);
}
