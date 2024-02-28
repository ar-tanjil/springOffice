package com.spring.office.payroll.repo;

import com.spring.office.employee.Employee;
import com.spring.office.payroll.domain.Additions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.YearMonth;
import java.util.Optional;

public interface AdditionsRepository extends JpaRepository<Additions, Long> {

    Optional<Additions> findByEmployeeAndPeriod(Employee employee, YearMonth period);
}
