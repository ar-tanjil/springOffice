package com.spring.office.payroll.repo;

import com.spring.office.payroll.domain.Additions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdditionsRepository extends JpaRepository<Additions, Long> {
}
