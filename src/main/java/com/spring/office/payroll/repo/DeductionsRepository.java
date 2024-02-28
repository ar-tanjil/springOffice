package com.spring.office.payroll.repo;

import com.spring.office.payroll.domain.Deductions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeductionsRepository  extends JpaRepository<Deductions, Long> {
}
