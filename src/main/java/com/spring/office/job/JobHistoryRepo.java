package com.spring.office.job;

import com.spring.office.job.JobHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobHistoryRepo extends JpaRepository<JobHistory, Long> {
}
