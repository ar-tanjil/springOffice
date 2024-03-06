package com.spring.office.payroll.repo;

import com.spring.office.payroll.domain.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface HolidayRepo extends JpaRepository<Holiday, Long> {

    Optional<Holiday> findByDay(LocalDate day);

}
