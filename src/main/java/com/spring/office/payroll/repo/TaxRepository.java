package com.spring.office.payroll.repo;

import com.spring.office.payroll.domain.Tax;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxRepository extends JpaRepository<Tax, Long> {
}
