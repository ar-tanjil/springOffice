package com.spring.office.payroll.repo;

import com.spring.office.payroll.domain.ClaimCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimCategoryRepo extends JpaRepository<ClaimCategory, Long> {
}
