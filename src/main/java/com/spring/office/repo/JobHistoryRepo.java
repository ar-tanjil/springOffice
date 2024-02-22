package com.spring.office.repo;

import com.spring.office.domain.JobHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobHistoryRepo extends JpaRepository<JobHistory, Long> {
}
