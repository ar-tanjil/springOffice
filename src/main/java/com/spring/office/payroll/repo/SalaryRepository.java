package com.spring.office.payroll.repo;

import com.spring.office.payroll.domain.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaryRepository extends JpaRepository<Salary, Long> {


}
