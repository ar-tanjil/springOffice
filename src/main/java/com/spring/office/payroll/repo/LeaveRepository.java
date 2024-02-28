package com.spring.office.payroll.repo;

import com.spring.office.payroll.domain.Leave;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRepository extends JpaRepository<Leave,Long> {
}
