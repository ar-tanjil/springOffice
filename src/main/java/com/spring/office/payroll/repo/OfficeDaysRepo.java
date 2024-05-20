package com.spring.office.payroll.repo;

import com.spring.office.payroll.domain.DayStatus;
import com.spring.office.payroll.domain.OfficeDays;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

public interface OfficeDaysRepo extends JpaRepository<OfficeDays, Long> {
    List<OfficeDays> findByStatus(DayStatus dayStatus);

    Optional<OfficeDays> findByDay(DayOfWeek day);
}
