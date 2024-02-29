package com.spring.office.payroll.repo;

import com.spring.office.employee.Employee;
import com.spring.office.payroll.domain.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Period;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

public interface PayrollRepository extends JpaRepository<Payroll, Long> {

    Optional<Payroll> findByEmployeeAndPeriod(Employee employee, YearMonth Period);

    int countByEmployeeAndPeriod(Employee employee, YearMonth Period);

    List<Payroll> findByPeriod(YearMonth period);
}
